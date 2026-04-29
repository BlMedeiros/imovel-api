package com.bruno.imovel.infrastructure.adapters.out.persistence.common.mapper;

import com.bruno.imovel.domain.property.core.Localization;
import com.bruno.imovel.infrastructure.adapters.out.persistence.common.LocalizationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationMapper {

    public LocalizationEntity toEntity(Localization domain) {
        return LocalizationEntity.builder()
                .street(domain.getStreet())
                .neighborhood(domain.getNeighborhood())
                .city(domain.getCity())
                .zipCode(domain.getZipCode())
                .geographicPoint(LocalizationEntity.toPoint(domain.getLatitude(), domain.getLongitude()))
                .build();
    }

    public Localization toDomain(LocalizationEntity entity) {
        return Localization.builder()
                .street(entity.getStreet())
                .neighborhood(entity.getNeighborhood())
                .city(entity.getCity())
                .zipCode(entity.getZipCode())
                .latitude(entity.getGeographicPoint().getY())
                .longitude(entity.getGeographicPoint().getX())
                .build();
    }
}
