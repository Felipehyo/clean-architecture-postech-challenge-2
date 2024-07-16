package com.postech.infra.dto.response;

import com.postech.domain.enums.EstadoPedidoEnum;
import com.postech.infra.dto.request.ClienteRequestDTO;

import java.util.List;

public record PedidoResponseDTO(Long id,
                                ClienteRequestDTO cliente,
                                EstadoPedidoEnum estado,
                                List<PedidoProdutoResponseDTO> pedidosProdutos) {
}
