package com.postech.infra.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProdutoRequestDTO {

    private Long produtoId;
    private Integer quantidade;

}
