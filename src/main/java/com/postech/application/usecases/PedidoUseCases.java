package com.postech.application.usecases;

import com.postech.application.gateways.RepositorioDePedidoGateway;
import com.postech.domain.entities.PedidoProduto;
import com.postech.domain.enums.EstadoPagamentoEnum;
import com.postech.domain.exceptions.PedidoException;
import com.postech.domain.entities.Pedido;
import com.postech.domain.enums.ErroPedidoEnum;
import com.postech.domain.enums.EstadoPedidoEnum;
import com.postech.domain.interfaces.PedidoServico;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.postech.application.utils.EstadoPedidoUtils.validaEstado;

public class PedidoUseCases implements PedidoServico {

    private final RepositorioDePedidoGateway repositorioDePedido;

    private final PagamentoUseCases pagamentoUseCases;

    private final ProdutoUseCases produtoUseCases;

    private final ClienteUseCases clienteUseCases;

    public PedidoUseCases(RepositorioDePedidoGateway repositorioDePedido, ProdutoUseCases produtoUseCases, ClienteUseCases clienteUseCases, PagamentoUseCases pagamentoUseCases) {
        this.repositorioDePedido = repositorioDePedido;
        this.produtoUseCases = produtoUseCases;
        this.clienteUseCases = clienteUseCases;
        this.pagamentoUseCases = pagamentoUseCases;
    }

    public Pedido consultaPorId(Long id) {
        Pedido pedido = repositorioDePedido.consultaPedidoPorId(id);

        if (pedido == null) {
            throw new PedidoException(ErroPedidoEnum.PEDIDO_NAO_ENCONTRADO);
        }

        return pedido;
    }

    public BigDecimal calcularValorPedido(Pedido pedido) {
        List<PedidoProduto> pedidosProdutos = pedido.getPedidosProdutos();

        double sum = pedidosProdutos.stream().mapToDouble(x -> x.getProduto().getPreco() * x.getQuantidade()).sum();

        return BigDecimal.valueOf(sum);
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


    public Pedido checkout(Long id) {
        EstadoPagamentoEnum estadoPagamento = pagamentoUseCases.getStatusPagamento(id);

        if(estadoPagamento.equals(EstadoPagamentoEnum.PAGO)){
            return this.atualizaEstadoPorIdDoPedido(id, EstadoPedidoEnum.PREPARANDO);
        }

        throw new PedidoException(ErroPedidoEnum.ESTADO_INVALIDO);
    }


    public void deleta(Long id) {
        try {
            repositorioDePedido.deletaPedido(id);
        } catch (Exception ignore) {
            throw new PedidoException(ErroPedidoEnum.PEDIDO_NAO_ENCONTRADO);
        }
    }

    public List<Pedido> listarPedidos() {

        List<Pedido> pedidos = consultaTodosOsPedidos();

        List<Pedido> pedidosFiltrados = filtrarPedidos(pedidos, List.of(EstadoPedidoEnum.CANCELADO, EstadoPedidoEnum.FINALIZADO));

        return ordenarListarPedidos(pedidosFiltrados);
    }

    private List<Pedido> ordenarListarPedidos(List<Pedido> pedidos) {
        return pedidos.stream()
                .sorted(Comparator.comparing((Pedido p) -> p.getEstado().getOrdem(), Comparator.reverseOrder())
                        .thenComparing(Pedido::getId))
                .toList();
    }

    public List<Pedido> filtrarPedidos(List<Pedido> pedidos, List<EstadoPedidoEnum> estadosParaRetirar){
        return pedidos.stream().filter(x -> !estadosParaRetirar.contains(x.getEstado())).collect(Collectors.toList());
    }
}
