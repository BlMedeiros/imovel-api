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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ResidentialProperty extends Property {

    private int bedrooms;

    private int parkingSpots;

    private boolean hasPool;

    public static ResidentialProperty create(Double price,
                                             Double totalArea,
                                             Localization localization,
                                             int bedrooms,
                                             int parkingSpots,
                                             boolean hasPool) {

        validate(price, totalArea, bedrooms, parkingSpots, localization);

        return ResidentialProperty.builder()
                .price(price)
                .totalArea(totalArea)
                .localization(localization)
                .bedrooms(bedrooms)
                .parkingSpots(parkingSpots)
                .hasPool(hasPool)
                .propertyStatus(PropertyStatus.DRAFT)
                .build();
    }

    private static void validate(Double price, Double totalArea, int bedrooms, int parkingSpots, Localization localization) {

        if (price != null && price <= 0) {
            throw new InvalidPriceException("O preço, quando informado, deve ser maior que zero.");
        }

        if (totalArea == null || totalArea <= 0) {
            throw new InvalidAreaException("A área total é obrigatória e deve ser maior que zero.");
        }

        if (bedrooms < 0) {
            throw new DomainValidationException("O número de salas não pode ser negativo.");
        }

        if (parkingSpots < 0) {
            throw new DomainValidationException("O número de banheiros não pode ser negativo.");
        }

        if (localization == null) {
            throw new DomainValidationException("A localização é obrigatória.");
        }

        if (localization.getLatitude() == null || localization.getLongitude() == null) {
            throw new DomainValidationException("As coordenadas geográficas (latitude e longitude) são obrigatórias.");
        }
    }

}
