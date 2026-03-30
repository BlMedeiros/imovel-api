package com.bruno.imovel.domain.property.types;

import com.bruno.imovel.domain.common.exception.DomainValidationException;
import com.bruno.imovel.domain.property.core.Localization;
import com.bruno.imovel.domain.property.core.Property;
import com.bruno.imovel.domain.property.core.PropertyStatus;
import com.bruno.imovel.domain.property.exception.InvalidAreaException;
import com.bruno.imovel.domain.property.exception.InvalidPriceException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class CommercialProperty extends Property {

    @Column(name = "office_rooms", nullable = false)
    private int officeRooms;          // salas / conjuntos

    @Column(nullable = false)
    private int bathrooms;            // banheiros

    @Column(name = "parking_spots", nullable = false)
    private int parkingSpots;         // vagas de garagem/estacionamento

    @Column(name = "is_street_front", nullable = false)
    private boolean isStreetFront;    // se é imóvel de frente para a rua

    public static CommercialProperty create(Double price,
                                            Double totalArea,
                                            Localization localization,
                                            int officeRooms,
                                            int bathrooms,
                                            int parkingSpots,
                                            boolean isStreetFront) {

        validate(price, totalArea, officeRooms, bathrooms, parkingSpots, localization);

        return CommercialProperty.builder()
                .price(price)
                .totalArea(totalArea)
                .localization(localization)
                .officeRooms(officeRooms)
                .bathrooms(bathrooms)
                .parkingSpots(parkingSpots)
                .isStreetFront(isStreetFront)
                .propertyStatus(PropertyStatus.DRAFT)
                .build();
    }

    private static void validate(Double price, Double totalArea, int officeRooms, int bathrooms, int parkingSpots, Localization localization) {

        if (price != null && price <= 0) {
            throw new InvalidPriceException("O preço, quando informado, deve ser maior que zero.");
        }

        if (totalArea == null || totalArea <= 0) {
            throw new InvalidAreaException("A área total é obrigatória e deve ser maior que zero.");
        }

        if (officeRooms < 0) {
            throw new DomainValidationException("O número de salas não pode ser negativo.");
        }

        if (bathrooms < 0) {
            throw new DomainValidationException("O número de banheiros não pode ser negativo.");
        }

        if (parkingSpots < 0) {
            throw new DomainValidationException("O número de vagas não pode ser negativo.");
        }

        if (localization == null || localization.getGeographicPoint() == null) {
            throw new DomainValidationException("A localização com coordenadas geográficas é obrigatória.");
        }
    }

}
