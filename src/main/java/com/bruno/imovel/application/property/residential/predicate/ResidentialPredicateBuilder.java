package com.bruno.imovel.application.property.residential.predicate;

import com.bruno.imovel.application.property.common.predicate.BasePredicateBuilder;
import com.bruno.imovel.application.property.residential.dto.ResidentialFilterDTO;
import com.bruno.imovel.infrastructure.adapters.out.persistence.residential.entity.QResidentialPropertyEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static com.bruno.imovel.application.property.common.predicate.PropertyPredicateHelper.*;

@Component
@RequiredArgsConstructor
public class ResidentialPredicateBuilder {

    private final BasePredicateBuilder baseBuilder;

    public Predicate build(ResidentialFilterDTO dto) {
        var q = QResidentialPropertyEntity.residentialPropertyEntity;

        BooleanBuilder builder = baseBuilder.buildBasePredicate(q._super, dto);

        Stream.of(
                ifNotNull(dto.bedrooms(),     () -> q.bedrooms.eq(dto.bedrooms())),
                ifNotNull(dto.bathrooms(),    () -> q.bathrooms.eq(dto.bathrooms())),
                ifNotNull(dto.parkingSpots(), () -> q.parkingSpots.eq(dto.parkingSpots())),
                ifTrue(dto.hasPool(),         q.hasPool::isTrue),
                ifTrue(dto.hasGarden(),       q.hasGarden::isTrue)
        ).forEach(builder::and);

        return builder;
    }
}
