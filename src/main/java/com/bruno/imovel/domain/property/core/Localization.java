package com.bruno.imovel.domain.property.core;

import jakarta.persistence.Embeddable;

@Embeddable
public class Localization {

    private String street;       // logradouro (inclui rua, avenida, etc.)
    private String complement;   // complemento
    private String neighborhood; // bairro
    private String city;         // cidade
    private String state;        // estado
    private String zipCode;      // cep
}

