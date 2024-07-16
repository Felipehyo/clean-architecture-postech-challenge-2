package com.postech.infra.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(

        @NotBlank(message = "O nome não pode estar vazio!")
        String nome,

        @NotBlank(message = "O email não pode estar vazio!")
        String email,

        CpfRequestDTO cpf

) {
}