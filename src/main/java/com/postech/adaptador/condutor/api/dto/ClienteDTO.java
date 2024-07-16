package com.postech.adaptador.condutor.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {


    private Long id;

    @NotBlank(message = "O nome não pode estar vazio!")
    private String nome;

    @NotBlank(message = "O email não pode estar vazio!")
    private String email;

    private CpfDTO cpf;
}
