package com.bruno.imovel.infrastructure.adapters.in.graphql.dto.input.residential;

import com.bruno.imovel.infrastructure.adapters.in.graphql.dto.input.common.LocalizationInput;

public record CreateResidentialPropertyInput(
        Double price,
        Double totalArea,
        LocalizationInput localization,
        Integer bedrooms,
        Integer parkingSpots,
        Boolean hasPool
) {
}
