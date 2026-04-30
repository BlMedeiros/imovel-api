package com.bruno.imovel.infrastructure.adapters.out.persistence.residential.mapper;

import com.bruno.imovel.domain.property.types.ResidentialProperty;
import com.bruno.imovel.infrastructure.adapters.out.persistence.common.LocalizationEntity;
import com.bruno.imovel.infrastructure.adapters.out.persistence.common.mapper.LocalizationMapper;
import com.bruno.imovel.infrastructure.adapters.out.persistence.residential.entity.ResidentialPropertyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResidentialPropertyMapper {

    private final LocalizationMapper localizationMapper;

    public ResidentialPropertyEntity toEntity(ResidentialProperty domain) {
        return ResidentialPropertyEntity.builder()
                .id(domain.getId())
                .price(domain.getPrice())
                .totalArea(domain.getTotalArea())
                .localization(localizationMapper.toEntity(domain.getLocalization()))
                .propertyStatus(domain.getPropertyStatus())
                .bedrooms(domain.getBedrooms())
                .bathrooms(domain.getBathrooms())
                .parkingSpots(domain.getParkingSpots())
                .hasPool(domain.isHasPool())
                .hasGarden(domain.isHasGarden())
                .build();
    }

    public ResidentialProperty toDomain(ResidentialPropertyEntity entity) {
        return ResidentialProperty.create(
                entity.getId(),
                entity.getPrice(),
                entity.getTotalArea(),
                localizationMapper.toDomain(entity.getLocalization()),
                entity.getPropertyStatus(),
                entity.getBedrooms(),
                entity.getBathrooms(),
                entity.getParkingSpots(),
                entity.isHasPool(),
                entity.isHasGarden()
                );
    }
}
