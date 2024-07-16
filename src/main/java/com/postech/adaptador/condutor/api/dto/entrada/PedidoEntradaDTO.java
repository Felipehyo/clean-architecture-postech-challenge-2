package com.postech.adaptador.condutor.api.dto.entrada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoEntradaDTO {

    private Long clienteId;

    private List<PedidoProdutoEntradaDTO> pedidosProdutos;

}
