package com.bruno.imovel.infrastructure.adapters.out.persistence.residential;

import com.bruno.imovel.domain.property.types.ResidentialProperty;
import com.bruno.imovel.infrastructure.adapters.in.graphql.dto.request.FilterResidentialPropertyResquest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaResidentialRepository extends JpaRepository<ResidentialProperty, Long>,
                                                  QuerydslPredicateExecutor<FilterResidentialPropertyResquest> {

    default void customize() {

    }
}
