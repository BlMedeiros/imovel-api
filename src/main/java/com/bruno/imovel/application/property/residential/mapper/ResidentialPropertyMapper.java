package com.bruno.imovel.application.property.residential.mapper;

import com.bruno.imovel.application.property.common.dto.LocalizationResponse;
import com.bruno.imovel.application.property.residential.dto.CreateResidentialPropertyRequest;
import com.bruno.imovel.application.property.residential.dto.CreateResidentialPropertyResponse;
import com.bruno.imovel.domain.property.core.Localization;
import com.bruno.imovel.domain.property.core.PropertyStatus;
import com.bruno.imovel.domain.property.types.ResidentialProperty;
import org.springframework.stereotype.Component;

@Component
public class ResidentialPropertyMapper {

    public ResidentialProperty toDomain(CreateResidentialPropertyRequest request) {

    }

    public CreateResidentialPropertyResponse fromDomain(ResidentialProperty property) {
        return new CreateResidentialPropertyResponse(
                property.getId(),
                property.getPrice(),
                property.getTotalArea(),
                LocalizationResponse.fromDomain(property.getLocalization()),
                property.getPropertyStatus(),
                property.getBedrooms(),
                property.getParkingSpots(),
                property.isHasPool()
        );
    }
}
