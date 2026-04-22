package com.bruno.imovel.application.property.common.predicate;

import com.bruno.imovel.application.property.common.dto.BaseFilterDTO;
import com.bruno.imovel.infrastructure.adapters.out.persistence.common.QAbstractPropertyEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static com.bruno.imovel.application.property.common.predicate.PropertyPredicateHelper.*;

@Component
public class BasePredicateBuilder {

    public BooleanBuilder buildBasePredicate(QAbstractPropertyEntity q, BaseFilterDTO dto) {
        var builder = new BooleanBuilder();

        Stream.of(
                ifNotBlank(dto.state(),        () -> q.localization.state.equalsIgnoreCase(dto.state())),
                ifNotBlank(dto.city(),         () -> q.localization.city.equalsIgnoreCase(dto.city())),
                ifNotBlank(dto.neighborhood(), () -> q.localization.neighborhood.equalsIgnoreCase(dto.neighborhood())),

                ifNotNull(dto.minPrice(),      () -> q.price.goe(dto.minPrice().doubleValue())),
                ifNotNull(dto.maxPrice(),      () -> q.price.loe(dto.maxPrice().doubleValue())),
                ifNotNull(dto.minArea(),       () -> q.totalArea.goe(dto.minArea())),
                ifNotNull(dto.maxArea(),       () -> q.totalArea.loe(dto.maxArea())),

                ifNotNull(dto.propertyStatus(),() -> q.propertyStatus.eq(dto.propertyStatus()))
        ).forEach(builder::and);

        return builder;
    }
}
