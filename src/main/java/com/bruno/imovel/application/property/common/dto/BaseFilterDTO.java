package com.bruno.imovel.application.property.common.dto;

import com.bruno.imovel.domain.property.core.PropertyStatus;

import java.math.BigDecimal;

public interface BaseFilterDTO {
    String state();
    String city();
    String neighborhood();

    BigDecimal minPrice();
    BigDecimal maxPrice();

    Double minArea();
    Double maxArea();

    GeoFilterDTO geoFilterDTO();

    PropertyStatus propertyStatus();
}
