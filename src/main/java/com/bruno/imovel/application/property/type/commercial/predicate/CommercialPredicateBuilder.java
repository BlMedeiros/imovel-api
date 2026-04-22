package com.bruno.imovel.application.property.type.commercial.predicate;

import com.bruno.imovel.application.property.type.commercial.dto.CommercialFilterDTO;
import com.bruno.imovel.infrastructure.adapters.out.persistence.commercial.entity.QCommercialPropertyEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static com.bruno.imovel.application.property.common.PropertyPredicateHelper.*;

@Component
public class CommercialPredicateBuilder {

    public Predicate build(CommercialFilterDTO dto) {
        var q = QCommercialPropertyEntity.commercialPropertyEntity;
        var builder = new BooleanBuilder();

        Stream.of(
                ifNotBlank(dto.state(),        () -> q.localization.state.equalsIgnoreCase(dto.state())),
                ifNotBlank(dto.city(),         () -> q.localization.city.equalsIgnoreCase(dto.city())),
                ifNotBlank(dto.neighborhood(), () -> q.localization.neighborhood.equalsIgnoreCase(dto.neighborhood())),

                ifNotNull(dto.minPrice(),      () -> q.price.goe(dto.minPrice().doubleValue())),
                ifNotNull(dto.maxPrice(),      () -> q.price.loe(dto.maxPrice().doubleValue())),
                ifNotNull(dto.minArea(),       () -> q.totalArea.goe(dto.minArea())),
                ifNotNull(dto.maxArea(),       () -> q.totalArea.loe(dto.maxArea())),

                ifNotNull(dto.officeRooms(),   () -> q.officeRooms.eq(dto.officeRooms())),
                ifNotNull(dto.bathrooms(),     () -> q.bathrooms.eq(dto.bathrooms())),
                ifNotNull(dto.parkingSpots(),  () -> q.parkingSpots.eq(dto.parkingSpots())),

                ifTrue(dto.isStreetFront(),    () -> q.isStreetFront.isTrue()),

                ifNotNull(dto.propertyStatus(),() -> q.propertyStatus.eq(dto.propertyStatus()))
        ).forEach(builder::and);

        return builder;
    }
}