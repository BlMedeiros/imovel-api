package com.bruno.imovel.infrastructure.adapters.in.graphql.dto.input.common;

public record LocalizationInput(
        String street,
        String neighborhood,
        String city,
        GeographicPointInput geographicPoint
) {
}
