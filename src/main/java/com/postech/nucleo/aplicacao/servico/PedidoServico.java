package com.postech.nucleo.aplicacao.servico;

import com.postech.adaptador.condutor.api.dto.PedidoDTO;
import com.postech.adaptador.condutor.api.dto.entrada.PedidoEntradaDTO;
import com.postech.nucleo.dominio.enums.EstadoPedidoEnum;

import java.util.List;

public interface PedidoServico {
    PedidoDTO consultaPedidoPorId(Long id);
    PedidoDTO criaPedido(PedidoEntradaDTO pedidodto);
    PedidoDTO atualizaEstadoPedidoPorId(Long id, EstadoPedidoEnum estado);
    EstadoPedidoEnum notificaEstadoPedido(Long id);
    List<PedidoDTO> consultaTodosPedidos();
    void fakeCheckout(Long id);

    void deletarPedido(Long id);
}
