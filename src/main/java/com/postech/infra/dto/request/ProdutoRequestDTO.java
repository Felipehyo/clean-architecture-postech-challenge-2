package com.postech.infra.dto.request;

import com.postech.domain.enums.CategoriaProdutoEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoRequestDTO {

    private Long id;

    @NotBlank(message = "O nome não pode estar vazio!")
    private String nome;

    @NotBlank(message = "A descrição não pode ser vazia!")
    private String descricao;

    @NotBlank(message = "A categoria não pode estar vazia!")
    private CategoriaProdutoEnum categoria;

    @Min(value = 0, message = "O valor do produto não pode ser negativo!")
    @NotNull(message = "O valor do produto não pode ser nulo")
    private Double preco;

}
