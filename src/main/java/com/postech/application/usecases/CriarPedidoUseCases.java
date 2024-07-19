package com.postech.application.usecases;

import com.postech.domain.entities.Cliente;
import com.postech.domain.entities.Pedido;
import com.postech.domain.entities.PedidoProduto;
import com.postech.domain.enums.EstadoPedidoEnum;
import com.postech.infra.dto.request.PedidoProdutoRequestDTO;
import com.postech.infra.dto.request.PedidoRequestDTO;

import java.util.ArrayList;
import java.util.List;


public class CriarPedidoUseCases {

    private final ProdutoUseCases produtoUseCases;

    private final ClienteUseCases clienteUseCases;

    private final PedidoUseCases pedidoUseCases;

    public CriarPedidoUseCases(ProdutoUseCases produtoUseCases, ClienteUseCases clienteUseCases, PedidoUseCases pedidoUseCases) {
        this.produtoUseCases = produtoUseCases;
        this.clienteUseCases = clienteUseCases;
        this.pedidoUseCases = pedidoUseCases;
    }

    public Pedido criaPedido(PedidoRequestDTO pedidoDTO){
        List<PedidoProdutoRequestDTO> pedidosProdutos = pedidoDTO.getPedidosProdutos();

        List<PedidoProduto> pedidoProdutos = new ArrayList<>();

        for (PedidoProdutoRequestDTO pedidosProduto : pedidosProdutos) {
            PedidoProduto pedidoProduto = new PedidoProduto();
            pedidoProduto.setProduto(produtoUseCases.consultaPorId(pedidosProduto.getProdutoId()));
            pedidoProduto.setQuantidade(pedidosProduto.getQuantidade());
            pedidoProdutos.add(pedidoProduto);
        }

        Cliente cliente = clienteUseCases.buscarPorId(pedidoDTO.getClienteId());

        return new Pedido(null, cliente, EstadoPedidoEnum.RECEBIDO, pedidoProdutos);
    }
}
