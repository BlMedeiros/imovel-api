package com.bruno.imovel.application.property.type.commercial.predicate;

import com.bruno.imovel.application.property.common.predicate.BasePredicateBuilder;
import com.bruno.imovel.application.property.type.commercial.dto.CommercialFilterDTO;
import com.bruno.imovel.infrastructure.adapters.out.persistence.commercial.entity.QCommercialPropertyEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static com.bruno.imovel.application.property.common.predicate.PropertyPredicateHelper.*;

@Component
@RequiredArgsConstructor
public class CommercialPredicateBuilder {

    private final BasePredicateBuilder baseBuilder;

    public Predicate build(CommercialFilterDTO dto) {
        var q = QCommercialPropertyEntity.commercialPropertyEntity;

        BooleanBuilder builder = baseBuilder.buildBasePredicate(q._super, dto);

        Stream.of(
                ifNotNull(dto.officeRooms(),   () -> q.officeRooms.eq(dto.officeRooms())),
                ifNotNull(dto.bathrooms(),     () -> q.bathrooms.eq(dto.bathrooms())),
                ifNotNull(dto.parkingSpots(),  () -> q.parkingSpots.eq(dto.parkingSpots())),

                ifTrue(dto.isStreetFront(),    () -> q.isStreetFront.isTrue())
        ).forEach(builder::and);

        return builder;
    }
}