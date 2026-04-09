package com.bruno.imovel.application.property.common;

import com.querydsl.core.types.dsl.BooleanExpression;
import io.micrometer.common.util.StringUtils;

import java.util.function.Supplier;

public interface PropertyPredicateHelper {

    static BooleanExpression ifNotBlank(String value, Supplier<BooleanExpression> expr) {
        return StringUtils.isNotBlank(value) ? expr.get() : null;
    }

    static <T> BooleanExpression ifNotNull(T value, Supplier<BooleanExpression> expr) {
        return value != null ? expr.get() : null;
    }

    static BooleanExpression ifTrue(Boolean value, Supplier<BooleanExpression> expr) {
        return Boolean.TRUE.equals(value) ? expr.get() : null;
    }
}
