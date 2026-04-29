package com.bruno.imovel.infrastructure.adapters.out.persistence.residential;

import com.bruno.imovel.domain.property.ports.out.PropertyRepositoryPort;
import com.bruno.imovel.domain.property.types.ResidentialProperty;
import com.bruno.imovel.infrastructure.adapters.out.persistence.residential.entity.ResidentialPropertyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ResidentialRepositoryAdapter implements PropertyRepositoryPort<ResidentialProperty> {

    private final JpaResidentialRepository repository;

    @Override
    public ResidentialProperty save(ResidentialProperty property) {

        ResidentialPropertyEntity residentialProperty = ResidentialPropertyEntity.create(property);

        ResidentialPropertyEntity saved = repository.save(residentialProperty);

        return residentialPropertyMapper.toDomain(saved);
    }

    @Override
    public ResidentialProperty update(ResidentialProperty property) {
        return null;
    }

    @Override
    public void delete(ResidentialProperty property) {

    }
}
