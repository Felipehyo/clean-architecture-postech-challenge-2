package com.postech.domain.interfaces;

import com.postech.domain.entities.Pedido;
import com.postech.infra.dto.request.PedidoRequestDTO;

public interface CriarPedido {

    Pedido criaPedido(PedidoRequestDTO pedidoDTO);
}
