package com.bruno.imovel.application.property.type.commercial.predicate;

import com.bruno.imovel.application.property.type.commercial.dto.CommercialFilterDTO;
import com.bruno.imovel.infrastructure.adapters.out.persistence.commercial.entity.QCommercialPropertyEntity;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class CommercialPredicateBuilder {

    public Predicate build(CommercialFilterDTO dto) {

        QCommercialPropertyEntity q = QCommercialPropertyEntity.commercialPropertyEntity;



    }
}
