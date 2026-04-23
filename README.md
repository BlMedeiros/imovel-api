# Imóvel API

API de gerenciamento imobiliário construída com Spring Boot, GraphQL e Clean Architecture. O projeto tem como foco principal um sistema de filtragem dinâmica de imóveis com suporte a consultas geoespaciais, permitindo que clientes busquem imóveis residenciais e comerciais com filtros flexíveis e combináveis.

---

## Tecnologias

| Tecnologia | Finalidade |
|---|---|
| Java 21 | Linguagem |
| Spring Boot 4.0 | Framework principal |
| Spring GraphQL | Camada de API |
| Spring Data JPA + Hibernate | Persistência |
| Hibernate Spatial | Suporte geoespacial |
| PostgreSQL + PostGIS | Banco de dados com consultas geográficas |
| QueryDSL | Construção de queries dinâmicas com type-safety |
| JTS (Java Topology Suite) | Operações com geometria |
| Lombok | Redução de boilerplate |
| Maven | Build |

---

## Arquitetura

O projeto segue **Clean Architecture** com a abordagem de Ports and Adapters (Hexagonal), organizado em três camadas principais:

```
com.bruno.imovel
├── domain/                        # Regras de negócio, entidades, portas
│   ├── common/exception/
│   └── property/
│       ├── core/                  # Property, Localization, PropertyStatus
│       ├── types/                 # ResidentialProperty, CommercialProperty
│       ├── exception/
│       └── ports/out/             # Contratos dos repositórios
│
├── application/                   # Casos de uso, DTOs, predicate builders
│   └── property/
│       ├── common/
│       │   ├── dto/               # BaseFilterDTO, GeoFilterDTO
│       │   └── predicate/         # BasePredicateBuilder, PropertyPredicateHelper
│       └── type/
│           ├── residential/       # ResidentialFilterDTO, ResidentialPredicateBuilder
│           └── commercial/        # CommercialFilterDTO, CommercialPredicateBuilder
│
└── infrastructure/                # Implementações técnicas
    └── adapters/
        ├── in/graphql/            # Resolvers e DTOs de entrada GraphQL
        └── out/persistence/       # Entidades JPA, repositórios, adapters
```

A **camada de domínio não possui nenhuma dependência** de Spring, JPA ou qualquer framework — é Java puro. Todas as preocupações de infraestrutura ficam na camada correspondente.

---

## Modelo de Domínio

### Hierarquia de Imóveis

```
Property (abstrato)
├── ResidentialProperty
└── CommercialProperty
```

**Property (base)**
- `id: Long`
- `price: BigDecimal`
- `totalArea: Double`
- `localization: Localization`
- `propertyStatus: PropertyStatus` — padrão `DRAFT`

**ResidentialProperty**
- `bedrooms: int`
- `bathrooms: int`
- `parkingSpots: int`
- `hasPool: boolean`
- `hasGarden: boolean`

**CommercialProperty**
- `officeRooms: int`
- `bathrooms: int`
- `parkingSpots: int`
- `isStreetFront: boolean`

**Localization** (objeto de valor)
- `street`, `neighborhood`, `city`, `state`, `zipCode`
- `latitude: Double`, `longitude: Double`
- Gera um `Point(SRID 4326)` do PostGIS para consultas geográficas

**PropertyStatus**
```
DRAFT → AVAILABLE → SOLD
```

---

### Validação de Domínio

Todos os objetos de domínio são criados por meio de **métodos de fábrica estáticos** que aplicam as invariantes antes da construção. Por exemplo, `ResidentialProperty.create(...)` valida:

- O preço, quando informado, deve ser maior que zero
- A área total é obrigatória e deve ser maior que zero
- Quartos, banheiros e vagas não podem ser negativos
- Localização com coordenadas geográficas válidas é obrigatória

---

## Sistema de Filtragem Dinâmica

O principal recurso do projeto é um sistema de filtragem dinâmica e type-safe construído sobre QueryDSL.

### DTOs de Filtro

```
BaseFilterDTO (interface)
├── state, city, neighborhood
├── minPrice, maxPrice
├── minArea, maxArea
├── propertyStatus
└── geoFilter: GeoFilterDTO

ResidentialFilterDTO (record) implements BaseFilterDTO
├── bedrooms, bathrooms, parkingSpots
├── hasPool, hasGarden
└── [todos os campos base]

CommercialFilterDTO (record) implements BaseFilterDTO
├── officeRooms, bathrooms, parkingSpots
├── isStreetFront
└── [todos os campos base]

GeoFilterDTO (record)
├── lat: Double
├── lon: Double
└── maxDistanceKm: Double
```

Records do Java 21 são utilizados para os DTOs — imutáveis, concisos, e com `equals`, `hashCode` e `toString` gerados automaticamente.

### Predicate Builders

Cada campo de filtro só é aplicado à query se o seu valor estiver presente. A interface `PropertyPredicateHelper` fornece três métodos utilitários:

```java
ifNotBlank(String value, Supplier<BooleanExpression> expr)  // para strings
ifNotNull(T value, Supplier<BooleanExpression> expr)         // para qualquer objeto
ifTrue(Boolean value, Supplier<BooleanExpression> expr)      // para booleanos
```

O padrão `Supplier<BooleanExpression>` adia a avaliação da expressão — a expressão QueryDSL só é construída se a condição for atendida.

O **BasePredicateBuilder** centraliza os filtros comuns a todos os tipos de imóvel (localização, preço, área, status e distância geográfica).

Os **builders específicos por tipo** (ResidentialPredicateBuilder, CommercialPredicateBuilder) compõem o builder base e adicionam seus próprios campos usando a mesma abordagem funcional com streams:

```java
Stream.of(
    ifNotNull(dto.bedrooms(),     () -> q.bedrooms.eq(dto.bedrooms())),
    ifNotNull(dto.bathrooms(),    () -> q.bathrooms.eq(dto.bathrooms())),
    ifTrue(dto.hasPool(),         q.hasPool::isTrue),
    ifTrue(dto.hasGarden(),       q.hasGarden::isTrue)
).forEach(builder::and);
```

### Filtro por Distância Geográfica

O `GeoFilterDTO` permite filtrar imóveis dentro de um raio a partir de um ponto de referência. Ele é aplicado a todos os tipos de imóvel pelo `BasePredicateBuilder`. A query usa `ST_DWithin` do PostGIS com cast para `geography`, garantindo que a distância seja calculada em metros e não em graus:

```sql
ST_DWithin(
  CAST(localization.geographicPoint AS geography),
  ST_SetSRID(ST_MakePoint(lon, lat), 4326)::geography,
  maxDistanceKm * 1000
)
```

---

## API GraphQL

### Queries

```graphql
searchResidentialProperties(filter: FilterResidentialProperty): [ResidentialProperty]
searchCommercialProperties(filter: FilterCommercialProperty): [CommercialProperty]
```

### Mutations

```graphql
createResidentialProperty(input: CreateResidentialPropertyInput): ResidentialProperty
createCommercialProperty(input: CreateCommercialPropertyInput): CommercialProperty
```

### Filtros de Entrada

**FilterResidentialProperty**
```graphql
input FilterResidentialProperty {
  state: String
  city: String
  neighborhood: String
  bedrooms: Int
  bathrooms: Int
  parkingSpots: Int
  minPrice: Float
  maxPrice: Float
  minArea: Float
  maxArea: Float
  maxDistanceKm: Float
  hasPool: Boolean
  propertyStatus: PropertyStatus
}
```

**FilterCommercialProperty**
```graphql
input FilterCommercialProperty {
  state: String
  city: String
  neighborhood: String
  officeRooms: Int
  bathrooms: Int
  parkingSpots: Int
  minPrice: Float
  maxPrice: Float
  minArea: Float
  maxArea: Float
  maxDistanceKm: Float
  isStreetFront: Boolean
  propertyStatus: PropertyStatus
}
```

### Inputs de Criação

```graphql
input CreateResidentialPropertyInput {
  price: Float
  totalArea: Float!
  localization: LocalizationInput!
  bedrooms: Int!
  parkingSpots: Int!
  hasPool: Boolean!
}

input CreateCommercialPropertyInput {
  price: Float
  totalArea: Float!
  localization: LocalizationInput!
  officeRooms: Int!
  bathrooms: Int!
  parkingSpots: Int!
  isStreetFront: Boolean!
}

input LocalizationInput {
  street: String!
  neighborhood: String!
  city: String!
  geographicPoint: GeographicPointInput!
}

input GeographicPointInput {
  latitude: Float!
  longitude: Float!
}
```

---

## Banco de Dados

O projeto requer **PostgreSQL com a extensão PostGIS** habilitada.

As coordenadas geográficas são armazenadas como colunas `geometry(Point, 4326)`, utilizando o sistema de referência de coordenadas WGS84 (padrão GPS).

Configure a conexão em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/imovel
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.database-platform=org.hibernate.spatial.dialect.postgis.PostgisDialect
```

---

## Executando o Projeto

**Requisitos:**
- Java 21
- Maven
- PostgreSQL com extensão PostGIS

**Passos:**

```bash
# Clone o repositório
git clone https://github.com/BlMedeiros/imobiliaria.git
cd imobiliaria

# Configure a conexão com o banco em:
# src/main/resources/application.properties

# Build e execução
mvn spring-boot:run
```

O playground do GraphQL estará disponível em `http://localhost:8080/graphiql`.

---

## Status do Projeto

O projeto está em desenvolvimento ativo. A fundação — modelo de domínio, sistema de filtros, entidades de persistência e schema GraphQL — está concluída. O que ainda está em andamento:

- [ ] Implementação dos adapters de repositório (mapeamento entidade ↔ domínio)
- [ ] Camada de serviço / casos de uso
- [ ] Resolvers GraphQL
- [ ] Global exception handler
- [ ] Autenticação e autorização (Spring Security)
- [ ] Gerenciamento de usuários e roles
- [ ] Testes unitários e de integração

---

## Autor

**Bruno Medeiros**
- GitHub: [@BlMedeiros](https://github.com/BlMedeiros)
