package com.bruno.imovel.infrastructure.adapters.out.persistence.commercial;

import com.bruno.imovel.domain.property.ports.out.PropertyRepositoryPort;
import com.bruno.imovel.domain.property.types.CommercialProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommercialRepositoryAdapter implements PropertyRepositoryPort<CommercialProperty> {

    private final JpaCommercialRepository jpaCommercialRepository;

    @Override
    public CommercialProperty save(CommercialProperty property) {
        return null;
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
