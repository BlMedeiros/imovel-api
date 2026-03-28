package com.bruno.imovel.domain.property.types;

import com.bruno.imovel.domain.property.core.Localization;
import com.bruno.imovel.domain.property.core.Property;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.locationtech.jts.geom.Point;

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
                                             Point geographicPoint,
                                             Localization localization,
                                             int bedrooms,
                                             int parkingSpots,
                                             boolean hasPool) {

        return ResidentialProperty.builder()
                .price(price)
                .totalArea(totalArea)
                .geographicPoint(geographicPoint)
                .localization(localization)
                .bedrooms(bedrooms)
                .parkingSpots(parkingSpots)
                .hasPool(hasPool)
                .build();
    }
}
