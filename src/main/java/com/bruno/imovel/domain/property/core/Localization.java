package com.bruno.imovel.domain.property.core;

import lombok.*;

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

