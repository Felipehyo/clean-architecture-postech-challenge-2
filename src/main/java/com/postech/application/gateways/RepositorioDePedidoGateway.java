package com.postech.application.gateways;

import com.postech.domain.entities.Pedido;

import java.util.List;

public interface RepositorioDePedidoGateway {
    Pedido consultaPedidoPorId(Long id);

    Pedido salvaPedido(Pedido pedido);

    void deletaPedido(Long id);

    List<Pedido> buscaTodosPedidos();

}
