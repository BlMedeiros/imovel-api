package com.bruno.imovel.application.property.usecases.residential;

import com.bruno.imovel.infrastructure.adapters.out.persistence.residential.ResidentialRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateResidentialPropertyUseCase {

    private final ResidentialRepositoryAdapter repository;

    public CreateResidentialPropertyResponse execute(CreateResidentialPropertyRequest request) {
        var property = request.toDomain();

        var saved = repository.save(property);

        return CreateResidentialPropertyResponse.fromDomain(saved);
    }
}
