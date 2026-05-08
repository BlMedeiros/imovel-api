package com.bruno.imovel.infrastructure.adapters.in.graphql.resolver.commercial;

import com.bruno.imovel.application.property.residential.dto.CreateResidentialPropertyResponse;
import com.bruno.imovel.application.property.residential.usecase.CreateResidentialPropertyUseCase;
import com.bruno.imovel.infrastructure.adapters.in.graphql.dto.input.residential.CreateResidentialPropertyInput;
import com.bruno.imovel.infrastructure.adapters.in.graphql.mapper.ResidentialPropertyInputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CommercialMutationResolver {

    private final CreateResidentialPropertyUseCase create;
    private final ResidentialPropertyInputMapper mapper;

    @MutationMapping
    public CreateResidentialPropertyResponse createResidentialProperty(@Argument CreateResidentialPropertyInput input) {
        var propertyRequest = mapper.toRequest(input);

        return create.execute(propertyRequest);
    }

}
