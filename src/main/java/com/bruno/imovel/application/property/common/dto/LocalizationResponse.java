package com.bruno.imovel.application.property.common.dto;

import com.bruno.imovel.domain.property.core.Localization;

public record LocalizationResponse(
        String street,
        String city
) {
    public static LocalizationResponse fromDomain(Localization localization) {
        return new LocalizationResponse(
                localization.getStreet(),
                localization.getCity()
        );
    }
}
