package com.bruno.imovel.infrastructure.adapters.in.graphql.mapper;

import com.bruno.imovel.application.property.common.dto.LocalizationRequest;
import com.bruno.imovel.application.property.residential.dto.CreateResidentialPropertyRequest;
import com.bruno.imovel.infrastructure.adapters.in.graphql.dto.input.residential.CreateResidentialPropertyInput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ResidentialPropertyInputMapper {

    public CreateResidentialPropertyRequest toRequest(CreateResidentialPropertyInput input) {
        var geo = input.localization().geographicPoint();

        var localization = new LocalizationRequest(
                input.localization().street(),
                input.localization().neighborhood(),
                input.localization().city(),
                geo.latitude(),
                geo.longitude()
        );

        return new CreateResidentialPropertyRequest(
                input.price() != null ? BigDecimal.valueOf(input.price()) : null,
                input.totalArea(),
                localization,
                input.bedrooms(),
                input.bathrooms(),
                input.parkingSpots(),
                input.hasPool(),
                input.hasGarden()
        );
    }
}
