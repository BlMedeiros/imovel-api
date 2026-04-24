package com.bruno.imovel.infrastructure.adapters.out.persistence.common;

import com.bruno.imovel.domain.property.core.Localization;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalizationEntity {

    @Column(nullable = false)
    private String street;

    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(length = 8,nullable = false)
    private String zipCode;

    @Column(columnDefinition = "geometry(Point, 4326)", nullable = false)
    private Point geographicPoint;

    public static Point toPoint(Double lat, Double lon) {
        if (lat == null || lon == null) return null;
        GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
        return factory.createPoint(new Coordinate(lon, lat));
    }

    public static LocalizationEntity create(Localization localization) {
        return new LocalizationEntity(
                localization.getStreet(),
                localization.getNeighborhood(),
                localization.getCity(),
                localization.getZipCode(),
                localization.getPoint()
        );
    }
}
