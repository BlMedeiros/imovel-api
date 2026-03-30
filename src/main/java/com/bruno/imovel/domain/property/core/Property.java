package com.bruno.imovel.domain.property.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public abstract class Property {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, name = "total_area")
    private Double totalArea;

    @Embedded
    @Column(nullable = false)
    private Localization localization;

    @Column(name = "property_status")
    @Enumerated(EnumType.STRING)
    private PropertyStatus propertyStatus = PropertyStatus.AVAILABLE;

}
