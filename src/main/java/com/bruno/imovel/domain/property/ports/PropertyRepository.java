package com.bruno.imovel.domain.property.ports;

import com.bruno.imovel.domain.property.core.Property;

public interface PropertyRepository<T extends Property> {

    T save(T property);
}
