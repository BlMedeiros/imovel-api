package com.bruno.imovel.domain.property.types;

import com.bruno.imovel.domain.property.core.Localization;
import com.bruno.imovel.domain.property.core.Property;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.locationtech.jts.geom.Point;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ComercialProperty extends Property {

    @Column(name = "office_rooms", nullable = false)
    private int officeRooms;          // salas / conjuntos

    @Column(nullable = false)
    private int bathrooms;            // banheiros (comercial costuma ter vários)

    @Column(name = "parking_spots",nullable = false)
    private int parkingSpots;         // vagas de garagem/estacionamento

    @Column(name = "is_street_front", nullable = false)
    private boolean isStreetFront;    // se é imóvel de frente para a rua

    public static ComercialProperty create(Double price,
                                             Double totalArea,
                                             Point geographicPoint,
                                             Localization localization,
                                             int officeRooms,
                                             int bathrooms,
                                             int parkingSpots,
                                             boolean isStreetFront) {

        return ComercialProperty.builder()
                .price(price)
                .totalArea(totalArea)
                .geographicPoint(geographicPoint)
                .localization(localization)
                .officeRooms(officeRooms)
                .bathrooms(bathrooms)
                .parkingSpots(parkingSpots)
                .isStreetFront(isStreetFront)
                .build();
    }

}
