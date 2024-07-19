package com.postech.application.usecases;

import com.postech.application.gateways.RepositorioDePedidoGateway;
import com.postech.domain.entities.Pedido;
import com.postech.domain.entities.PedidoProduto;

import java.util.List;

public class SalvarPedidoUseCases {

    private final RepositorioDePedidoGateway repositorioDePedidoGateway;

    public SalvarPedidoUseCases(RepositorioDePedidoGateway repositorioDePedidoGateway) {
        this.repositorioDePedidoGateway = repositorioDePedidoGateway;
    }

    public Pedido salvarPedido(Pedido pedido){
        List<PedidoProduto> pedidosProdutos = pedido.getPedidosProdutos();

        pedido.setPedidosProdutos(null);

        Pedido pedidoSalvo = repositorioDePedidoGateway.salvaPedido(pedido);

        pedidosProdutos.forEach(x -> {
            x.setPedido(pedidoSalvo);
        });

        pedidoSalvo.setPedidosProdutos(pedidosProdutos);

        return repositorioDePedidoGateway.salvaPedido(pedidoSalvo);
    }
}
