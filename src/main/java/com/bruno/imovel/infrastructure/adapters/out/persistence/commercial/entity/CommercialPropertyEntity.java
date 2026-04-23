package com.bruno.imovel.infrastructure.adapters.out.persistence.commercial.entity;

import com.bruno.imovel.infrastructure.adapters.out.persistence.common.AbstractPropertyEntity;
import com.bruno.imovel.infrastructure.adapters.out.persistence.common.LocalizationEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "commercial_properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommercialPropertyEntity extends AbstractPropertyEntity {

    @Column(name = "office_rooms", nullable = false)
    private int officeRooms;

    @Column(nullable = false)
    private int bathrooms;

    @Column(name = "parking_spots", nullable = false)
    private int parkingSpots;

    @Column(name = "is_street_front", nullable = false)
    private boolean isStreetFront;

}
