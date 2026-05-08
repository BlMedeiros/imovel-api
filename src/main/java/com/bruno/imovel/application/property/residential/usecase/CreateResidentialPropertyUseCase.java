package com.bruno.imovel.application.property.residential.usecase;

import com.bruno.imovel.application.property.residential.dto.CreateResidentialPropertyRequest;
import com.bruno.imovel.application.property.residential.dto.CreateResidentialPropertyResponse;
import com.bruno.imovel.application.property.residential.mapper.ResidentialPropertyMapper;
import com.bruno.imovel.infrastructure.adapters.out.persistence.residential.ResidentialRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateResidentialPropertyUseCase {

    private final ResidentialRepositoryAdapter repository;
    private final ResidentialPropertyMapper mapper;

    public CreateResidentialPropertyResponse execute(CreateResidentialPropertyRequest request) {
        var property = request.toDomain();

        var saved = repository.save(property);

        return mapper.fromDomain(saved);
    }
}
