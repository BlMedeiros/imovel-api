package com.bruno.imovel.application.property.residential.dto;

import com.bruno.imovel.application.property.common.dto.LocalizationResponse;
import com.bruno.imovel.domain.property.core.PropertyStatus;
import com.bruno.imovel.domain.property.types.ResidentialProperty;

import java.math.BigDecimal;

public record CreateResidentialPropertyResponse(
        Long id,
        BigDecimal price,
        Double totalArea,
        LocalizationResponse localization,
        PropertyStatus propertyStatus,
        Integer bedrooms,
        Integer parkingSpots,
        Boolean hasPool
) { }
