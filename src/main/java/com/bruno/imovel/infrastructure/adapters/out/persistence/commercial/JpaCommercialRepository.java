package com.bruno.imovel.infrastructure.adapters.out.persistence.commercial;

import com.bruno.imovel.domain.property.types.CommercialProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCommercialRepository extends JpaRepository<CommercialProperty, Long>,
                                                 QuerydslPredicateExecutor<CommercialProperty> {

}