package com.postech.adaptador.condutor.api.dto.entrada;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProdutoEntradaDTO {

    private Long produtoId;
    private Integer quantidade;

}
