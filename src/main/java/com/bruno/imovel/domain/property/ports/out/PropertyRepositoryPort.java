package com.bruno.imovel.domain.property.ports.out;

import com.bruno.imovel.domain.property.core.Property;

public interface PropertyRepositoryPort<T extends Property> {

    T save(T property);

    T update(T property);

    void delete(Long id);
}
