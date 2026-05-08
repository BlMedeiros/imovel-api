package com.bruno.imovel.application.property.common.dto;

public record LocalizationRequest(
        String street,
        String neighborhood,
        String city,
        Double latitude,
        Double longitude
) {
}
