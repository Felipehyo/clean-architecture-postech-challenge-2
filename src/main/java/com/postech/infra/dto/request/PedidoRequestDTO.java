package com.postech.infra.dto.request;

import com.postech.domain.enums.EstadoPedidoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoRequestDTO {

    private Long clienteId;

    private List<PedidoProdutoRequestDTO> pedidosProdutos;

}
