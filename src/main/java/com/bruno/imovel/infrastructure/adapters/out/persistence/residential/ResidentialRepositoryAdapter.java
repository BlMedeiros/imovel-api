package com.bruno.imovel.infrastructure.adapters.out.persistence.residential;

import com.bruno.imovel.domain.property.ports.out.PropertyRepositoryPort;
import com.bruno.imovel.domain.property.types.ResidentialProperty;
import com.bruno.imovel.infrastructure.adapters.out.persistence.residential.entity.ResidentialPropertyEntity;
import com.bruno.imovel.infrastructure.adapters.out.persistence.residential.mapper.ResidentialPropertyMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ResidentialRepositoryAdapter implements PropertyRepositoryPort<ResidentialProperty> {

    private final JpaResidentialRepository repository;
    private final ResidentialPropertyMapper mapper;

    @Override
    public ResidentialProperty save(ResidentialProperty property) {

        ResidentialPropertyEntity residentialProperty = mapper.toEntity(property);

        ResidentialPropertyEntity saved = repository.save(residentialProperty);

        return mapper.toDomain(saved);
    }

    @Override
    public ResidentialProperty update(ResidentialProperty property) {
        if(!repository.existsById(property.getId())) {
            throw new EntityNotFoundException("Residential Property not found with id: " + property.getId());
        }

        ResidentialPropertyEntity toUpdate = mapper.toEntity(property);
        ResidentialPropertyEntity updated = repository.save(toUpdate);

        return mapper.toDomain(updated);
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException("Residential Property not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
