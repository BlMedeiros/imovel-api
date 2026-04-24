package com.bruno.imovel.infrastructure.adapters.out.persistence.residential;

import com.bruno.imovel.infrastructure.adapters.out.persistence.residential.entity.ResidentialPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaResidentialRepository extends JpaRepository<ResidentialPropertyEntity, Long>,
                                                  QuerydslPredicateExecutor<ResidentialPropertyEntity> {
}
