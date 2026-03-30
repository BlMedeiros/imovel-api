package com.bruno.imovel.domain.property.core;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

@Embeddable
@Getter
@Setter
public class Localization {

    private String street;
    private String neighborhood;
    private String city;

    @Column(length = 8)
    private String zipCode;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point geographicPoint;

    public static Point createPoint(Double lat, Double lon) {
        GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
        return factory.createPoint(new Coordinate(lon, lat));
    }
}

