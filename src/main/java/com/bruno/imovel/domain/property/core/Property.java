package com.bruno.imovel.domain.property.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public abstract class Property {
    private Long id;
    private BigDecimal price;
    private Double totalArea;
    private Localization localization;
    private PropertyStatus propertyStatus;
}
