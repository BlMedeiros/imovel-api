package com.bruno.imovel.infrastructure.adapters.out.persistence.residential.entity;

import com.bruno.imovel.domain.property.types.CommercialProperty;
import com.bruno.imovel.domain.property.types.ResidentialProperty;
import com.bruno.imovel.infrastructure.adapters.out.persistence.commercial.entity.CommercialPropertyEntity;
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
import org.springframework.cglib.core.Local;

@Entity
@Table(name = "residential_properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ResidentialPropertyEntity extends AbstractPropertyEntity {

    private int bedrooms;

    private int bathrooms;

    @Column(name = "parking_spots")
    private int parkingSpots;

    @Column(name = "has_pool")
    private boolean hasPool;

    @Column(name = "has_garden")
    private boolean hasGarden;

}

