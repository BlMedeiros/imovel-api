package com.bruno.imovel.infrastructure.adapters.in.graphql.resolver.residential;

import com.bruno.imovel.application.property.residential.usecase.CreateResidentialPropertyUseCase;
import com.bruno.imovel.domain.property.types.ResidentialProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ResidentialMutationResolver {

    private final CreateResidentialPropertyUseCase create;

    @MutationMapping
    public ResidentialProperty createResidentialProperty(@Argument CreateResidentialPropertyRequest request) {

    }

}
