package com.bruno.imovel.infrastructure.adapters.out.persistence.commercial;

import com.bruno.imovel.domain.property.ports.out.PropertyRepositoryPort;
import com.bruno.imovel.domain.property.types.CommercialProperty;
import com.bruno.imovel.infrastructure.adapters.out.persistence.commercial.entity.CommercialPropertyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommercialRepositoryAdapter implements PropertyRepositoryPort<CommercialProperty> {

    private final JpaCommercialRepository repository;

    @Override
    public CommercialProperty save(CommercialProperty property) {
        CommercialPropertyEntity commercialProperty = CommercialPropertyEntity.create(property);

        CommercialPropertyEntity saved = repository.save(commercialProperty);

        return commercialPropertyMapper.toDomain(saved);
    }

    @Override
    public CommercialProperty update(CommercialProperty property) {
        return null;
    }

    @Override
    public CommercialProperty delete(CommercialProperty property) {
        return null;
    }
}
