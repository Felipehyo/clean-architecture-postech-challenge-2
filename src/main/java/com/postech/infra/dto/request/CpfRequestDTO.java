package com.postech.infra.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CpfRequestDTO {

    @NotBlank(message = "O numero de CPF não pode estar vazio")
    private String numero;
}
