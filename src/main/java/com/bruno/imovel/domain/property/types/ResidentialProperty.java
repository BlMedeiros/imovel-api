package com.bruno.imovel.domain.property.types;

import com.bruno.imovel.domain.common.exception.DomainValidationException;
import com.bruno.imovel.domain.property.core.Localization;
import com.bruno.imovel.domain.property.core.Property;
import com.bruno.imovel.domain.property.core.PropertyStatus;
import com.bruno.imovel.domain.property.exception.InvalidAreaException;
import com.bruno.imovel.domain.property.exception.InvalidPriceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ResidentialProperty extends Property {

    private int bedrooms;

    private int bathrooms;

    private int parkingSpots;

    private boolean hasPool;

    private boolean hasGarden;

    public static ResidentialProperty create(Long id,
                                             BigDecimal price,
                                             Double totalArea,
                                             Localization localization,
                                             PropertyStatus propertyStatus,
                                             int bedrooms,
                                             int bathrooms,
                                             int parkingSpots,
                                             boolean hasPool,
                                             boolean hasGarden) {

        validate(price, totalArea,propertyStatus, bedrooms, bathrooms, parkingSpots, localization);

        return ResidentialProperty.builder()
                .id(id)
                .price(price)
                .totalArea(totalArea)
                .localization(localization)
                .propertyStatus(propertyStatus)
                .bedrooms(bedrooms)
                .bathrooms(bathrooms)
                .parkingSpots(parkingSpots)
                .hasPool(hasPool)
                .hasGarden(hasGarden)
                .build();
    }

    private static void validate(BigDecimal price, Double totalArea,PropertyStatus propertyStatus, int bedrooms, int bathrooms, int parkingSpots, Localization localization) {

        if (price != null && price.compareTo(BigDecimal.ZERO) == 0) {
            throw new InvalidPriceException("O preço, quando informado, deve ser maior que zero.");
        }

        if (totalArea == null || totalArea <= 0) {
            throw new InvalidAreaException("A área total é obrigatória e deve ser maior que zero.");
        }

        if (bedrooms < 0) {
            throw new DomainValidationException("O número de quartos não pode ser negativo.");
        }

        if (bathrooms < 0) {
            throw new DomainValidationException("O número de banheiros não pode ser negativo.");
        }

        if (parkingSpots < 0) {
            throw new DomainValidationException("O número de vagas não pode ser negativo.");
        }

        if (localization == null) {
            throw new DomainValidationException("A localização é obrigatória.");
        }

        if (localization.getLatitude() == null || localization.getLongitude() == null) {
            throw new DomainValidationException("As coordenadas geográficas (latitude e longitude) são obrigatórias.");
        }

        if(propertyStatus == null) {
            throw new DomainValidationException("O status do imóvel é obrigatório.");
        }

        }
    }

}
