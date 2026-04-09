package com.bruno.imovel.application.property.type.commercial.dto;

import com.bruno.imovel.domain.property.core.PropertyStatus;

import java.math.BigDecimal;

public record CommercialFilterDTO(
        String state,
        String city,
        String neighborhood,

        Integer officeRooms,
        Integer bathrooms,
        Integer parkingSpots,
        Boolean isStreetFront,

        BigDecimal minPrice,
        BigDecimal maxPrice,
        Double minArea,
        Double maxArea,

        Double maxDistanceKm,
        PropertyStatus propertyStatus

) {
}
