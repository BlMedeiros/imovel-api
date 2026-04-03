package com.bruno.imovel.domain.property.core;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Localization {
    private String street;
    private String neighborhood;
    private String city;
    private String zipCode;
    private Double latitude;
    private Double longitude;

    public static Localization create(String street, String city, Double lat, Double lon) {
        return Localization.builder()
                .street(street)
                .city(city)
                .latitude(lat)
                .longitude(lon)
                .build();
    }
}

