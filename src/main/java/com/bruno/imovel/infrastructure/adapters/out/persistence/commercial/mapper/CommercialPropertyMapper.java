package com.bruno.imovel.infrastructure.adapters.out.persistence.commercial.mapper;

import com.bruno.imovel.domain.property.types.CommercialProperty;
import com.bruno.imovel.infrastructure.adapters.out.persistence.commercial.entity.CommercialPropertyEntity;
import com.bruno.imovel.infrastructure.adapters.out.persistence.common.LocalizationEntity;
import com.bruno.imovel.infrastructure.adapters.out.persistence.common.mapper.LocalizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommercialPropertyMapper {

    private final LocalizationMapper localizationMapper;

    public CommercialPropertyEntity toEntity(CommercialProperty domain) {
        return CommercialPropertyEntity.builder()
                .id(domain.getId())
                .price(domain.getPrice())
                .totalArea(domain.getTotalArea())
                .localization(LocalizationEntity.create(domain.getLocalization()))
                .propertyStatus(domain.getPropertyStatus())
                .officeRooms(domain.getOfficeRooms())
                .bathrooms(domain.getBathrooms())
                .parkingSpots(domain.getParkingSpots())
                .isStreetFront(domain.isStreetFront())
                .build();
    }

    public CommercialProperty toDomain(CommercialPropertyEntity entity) {
        return CommercialProperty.create(
                entity.getId(),
                entity.getPrice(),
                entity.getTotalArea(),
                localizationMapper.toDomain(entity.getLocalization()),
                entity.getPropertyStatus(),
                entity.getOfficeRooms(),
                entity.getBathrooms(),
                entity.getParkingSpots(),
                entity.isStreetFront()
        );
    }
}
