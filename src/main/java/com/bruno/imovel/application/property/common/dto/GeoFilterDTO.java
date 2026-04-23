package com.bruno.imovel.application.property.common.dto;

public record GeoFilterDTO(
        Double lat,
        Double lon,
        Double maxDistanceKm
) {
}
