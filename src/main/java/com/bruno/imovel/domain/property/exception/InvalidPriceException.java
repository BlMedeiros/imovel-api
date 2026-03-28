package com.bruno.imovel.domain.property.exception;

import com.bruno.imovel.domain.common.exception.DomainValidationException;

public class InvalidPriceException extends DomainValidationException {
    public InvalidPriceException(String message) {
        super(message);
    }
}
