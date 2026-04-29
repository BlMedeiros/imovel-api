package com.bruno.imovel.infrastructure.adapters.out.persistence.commercial;

import com.bruno.imovel.domain.property.ports.out.PropertyRepositoryPort;
import com.bruno.imovel.domain.property.types.CommercialProperty;
import com.bruno.imovel.infrastructure.adapters.out.persistence.commercial.entity.CommercialPropertyEntity;
import com.bruno.imovel.infrastructure.adapters.out.persistence.commercial.mapper.CommercialPropertyMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommercialRepositoryAdapter implements PropertyRepositoryPort<CommercialProperty> {

    private final JpaCommercialRepository repository;
    private final CommercialPropertyMapper mapper;

    @Override
    public CommercialProperty save(CommercialProperty property) {
        CommercialPropertyEntity toSave = mapper.toEntity(property);

        CommercialPropertyEntity saved = repository.save(toSave);

        return mapper.toDomain(saved);
    }

    @Override
    public CommercialProperty update(CommercialProperty property) {
        if (!repository.existsById(property.getId())) {
            throw new EntityNotFoundException("CommercialProperty not found with id: " + property.getId());
        }

        CommercialPropertyEntity toUpdate = mapper.toEntity(property);
        CommercialPropertyEntity updated = repository.save(toUpdate);

        return mapper.toDomain(updated);
    }

    @Override
    public void delete(CommercialProperty property) {
        if (!repository.existsById(property.getId())) {
            throw new EntityNotFoundException("CommercialProperty not found with id: " + property.getId());
        }

        repository.deleteById(property.getId());
    }
}
