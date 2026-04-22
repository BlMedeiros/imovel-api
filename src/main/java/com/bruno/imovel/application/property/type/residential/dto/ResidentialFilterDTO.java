package com.bruno.imovel.application.property.type.residential.dto;

import com.bruno.imovel.application.property.common.dto.BaseFilterDTO;
import com.bruno.imovel.domain.property.core.PropertyStatus;

import java.math.BigDecimal;

public record ResidentialFilterDTO(
        String state,
        String city,
        String neighborhood,

        BigDecimal minPrice,
        BigDecimal maxPrice,
        Double minArea,
        Double maxArea,
        Double maxDistanceKm,

        Integer bedrooms,
        Integer bathrooms,
        Integer parkingSpots,

        Boolean hasGarden,
        Boolean hasPool,

        PropertyStatus propertyStatus
) implements BaseFilterDTO {
}
