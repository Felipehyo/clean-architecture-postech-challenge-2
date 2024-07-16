package com.postech.application.usecases;

import com.postech.application.gateways.RepositorioDePedidoGateway;
import com.postech.domain.exceptions.PedidoException;
import com.postech.domain.entities.Pedido;
import com.postech.domain.entities.PedidoProduto;
import com.postech.domain.enums.ErroPedidoEnum;
import com.postech.domain.enums.EstadoPedidoEnum;

import java.util.ArrayList;
import java.util.List;

import static com.postech.application.utils.EstadoPedidoUtils.validaEstado;

public class PedidoUseCases {

    private final RepositorioDePedidoGateway repositorioDePedido;

    private final ProdutoUseCases produtoUseCases;

    private final ClienteUseCases clienteUseCases;

    public PedidoUseCases(RepositorioDePedidoGateway repositorioDePedido, ProdutoUseCases produtoUseCases, ClienteUseCases clienteUseCases) {
        this.repositorioDePedido = repositorioDePedido;
        this.produtoUseCases = produtoUseCases;
        this.clienteUseCases = clienteUseCases;
    }

    public Pedido consultaPorId(Long id) {
        Pedido pedido = repositorioDePedido.consultaPedidoPorId(id);

        if (pedido == null) {
            throw new PedidoException(ErroPedidoEnum.PEDIDO_NAO_ENCONTRADO);
        }

        return pedido;
    }


    public Pedido cadastrar(Pedido pedido) {

        var cliente = clienteUseCases.buscarPorId(pedido.getCliente().getId());
        var listaProdutos = new ArrayList<PedidoProduto>();

        Pedido novoPedido = repositorioDePedido.salvaPedido(new Pedido(null, cliente, EstadoPedidoEnum.PENDENTE_PAGAMENTO, null));

        for (PedidoProduto pedidoProduto : pedido.getPedidosProdutos()) {
            var produto = produtoUseCases.consultaPorId(pedidoProduto.getProduto().getId());
            listaProdutos.add(new PedidoProduto(null, novoPedido, produto, pedidoProduto.getQuantidade()));
        }

        novoPedido.setPedidosProdutos(listaProdutos);
        return repositorioDePedido.salvaPedido(novoPedido);
    }


    public Pedido atualizaEstadoPorIdDoPedido(Long idDoPedido, EstadoPedidoEnum estado) {
        Pedido pedido = this.consultaPorId(idDoPedido);

        if (!validaEstado(pedido.getEstado(), estado)) {
            throw new PedidoException(ErroPedidoEnum.ESTADO_INVALIDO);
        }

        pedido.setEstado(estado);

        return repositorioDePedido.salvaPedido(pedido);

    }

    public Pedido notificaEstado(Long id) {
        return this.consultaPorId(id);
    }

    public List<Pedido> consultaTodosOsPedidos() {
        List<Pedido> pedidos = repositorioDePedido.buscaTodosPedidos();

        if (pedidos.isEmpty()) {
            throw new PedidoException(ErroPedidoEnum.PEDIDOS_NAO_ECONTRADOS);
        }

        return pedidos;
    }


    public void checkout(Long id) {
        this.atualizaEstadoPorIdDoPedido(id, EstadoPedidoEnum.PAGO);
        this.atualizaEstadoPorIdDoPedido(id, EstadoPedidoEnum.RECEBIDO);
    }


    public void deleta(Long id) {
        try {
            repositorioDePedido.deletaPedido(id);
        } catch (Exception ignore) {
            throw new PedidoException(ErroPedidoEnum.PEDIDO_NAO_ENCONTRADO);
        }
    }

}
