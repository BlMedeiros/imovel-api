package com.bruno.imovel.domain.property.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.locationtech.jts.geom.Point;

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

    @Column(nullable = false, name = "geographic_point", columnDefinition = "geometry(Point, 4326)")
    private Point geographicPoint;

    @Embedded
    @Column(nullable = false)
    private Localization localization;

    @Column(name = "property_status")
    @Enumerated(EnumType.STRING)
    private PropertyStatus propertyStatus = PropertyStatus.AVAILABLE;

}
