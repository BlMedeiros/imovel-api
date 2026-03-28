package com.bruno.imovel.domain.property.exception;

import com.bruno.imovel.domain.common.exception.DomainValidationException;

public class InvalidAreaException extends DomainValidationException {
    public InvalidAreaException(String message) {
        super(message);
    }
}
