package com.bruno.imovel.domain.property.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public abstract class Property {
    private Long id;
    private Double price;
    private Double totalArea;
    private Localization localization;
    private PropertyStatus propertyStatus;
}
