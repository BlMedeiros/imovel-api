package com.bruno.imovel.insfrastructure.adapters.out.persistence.residential;

import com.bruno.imovel.domain.property.ports.out.PropertyRepositoryPort;
import com.bruno.imovel.domain.property.types.ResidentialProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ResidentialRepositoryAdapter implements PropertyRepositoryPort<ResidentialProperty> {

    private final JpaResidentialRepository repository;

    @Override
    public ResidentialProperty save(ResidentialProperty property) {
        return null;
    }

    @Override
    public ResidentialProperty update(ResidentialProperty property) {
        return null;
    }

    @Override
    public ResidentialProperty delete(ResidentialProperty property) {
        return null;
    }
}
