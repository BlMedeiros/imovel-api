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
public class ResidentialProperty extends Property {

    @Column(nullable = false)
    private int bedrooms;

    @Column(name = "parking_spots", nullable = false)
    private int parkingSpots;

    @Column(name = "has_pool", nullable = false)
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

        if (localization == null || localization.getGeographicPoint() == null) {
            throw new DomainValidationException("A localização com coordenadas geográficas é obrigatória.");
        }
    }

}
