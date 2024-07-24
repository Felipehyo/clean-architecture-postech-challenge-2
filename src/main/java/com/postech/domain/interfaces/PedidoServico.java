package com.postech.domain.interfaces;


import com.postech.domain.entities.Pedido;
import com.postech.domain.enums.EstadoPedidoEnum;

import java.math.BigDecimal;
import java.util.List;

public interface PedidoServico {

    Pedido consultaPorId(Long id);
    BigDecimal calcularValorPedido(Pedido pedido);
    Pedido atualizaEstadoPorIdDoPedido(Long idDoPedido, EstadoPedidoEnum estado);
    Pedido notificaEstado(Long id);
    List<Pedido> consultaTodosOsPedidos();
    Pedido checkout(Long id);
    void deleta(Long id);
    List<Pedido> listarPedidos();


}
