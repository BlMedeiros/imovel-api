package com.bruno.imovel.insfrastructure.adapters.out.persistence.residential;

import com.bruno.imovel.domain.property.types.ResidentialProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaResidentialRepository extends JpaRepository<ResidentialProperty, Long> {
}
