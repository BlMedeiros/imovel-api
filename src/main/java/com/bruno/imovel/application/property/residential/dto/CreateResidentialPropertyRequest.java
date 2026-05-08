package com.bruno.imovel.application.property.residential.dto;

import com.bruno.imovel.application.property.common.dto.LocalizationRequest;
import com.bruno.imovel.domain.property.core.Localization;
import com.bruno.imovel.domain.property.core.PropertyStatus;
import com.bruno.imovel.domain.property.types.ResidentialProperty;

import java.math.BigDecimal;

public record CreateResidentialPropertyRequest(
        BigDecimal price,
        Double totalArea,
        LocalizationRequest localization,
        Integer bedrooms,
        Integer bathrooms,
        Integer parkingSpots,
        Boolean hasPool,
        Boolean hasGarden
) {
    public ResidentialProperty toDomain() {
        var loc = Localization.builder()
                .street(localization.street())
                .neighborhood(localization.neighborhood())
                .city(localization.city())
                .latitude(localization.latitude())
                .longitude(localization.longitude())
                .build();

        return ResidentialProperty.create(
                null,
                price,
                totalArea,
                loc,
                PropertyStatus.DRAFT,
                bedrooms != null ? bedrooms : 0,
                bathrooms != null ? bathrooms : 0,
                parkingSpots != null ? parkingSpots : 0,
                hasPool != null && hasPool,
                hasGarden != null && hasGarden
        );
    }
}
