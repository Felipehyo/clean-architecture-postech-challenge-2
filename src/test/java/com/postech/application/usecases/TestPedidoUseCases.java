package com.postech.application.usecases;

import com.postech.application.gateways.RepositorioDePedidoGateway;
import com.postech.domain.entities.*;
import com.postech.domain.enums.CategoriaProdutoEnum;
import com.postech.domain.enums.EstadoPedidoEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.List;


public class TestPedidoUseCases {

    @Mock
    private RepositorioDePedidoGateway repositorioDePedido;

    @Mock
    private PagamentoUseCases pagamentoUseCases;

    @Mock
    private ProdutoUseCases produtoUseCases;

    @Mock
    private ClienteUseCases clienteUseCases;

    @Spy
    @InjectMocks
    private PedidoUseCases pedidoUseCases;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testOrdenarListarPedidosPorAntiguidade(){
        Cliente cliente = getCliente(1L, "teste", "teste@teste.com", new CPF("12345678909"));

        Produto produto = getProduto(1L, "LANCHE", "LANCHE", CategoriaProdutoEnum.LANCHE, 2.0);
        Pedido pedido = getPedido(1L, cliente, EstadoPedidoEnum.RECEBIDO, null);
        PedidoProduto pedidoProduto = getPedidoProduto(1L, pedido, produto, 1);
        pedido.setPedidosProdutos(List.of(pedidoProduto));


        Produto produto2 = getProduto(1L, "SOBREMESA", "SOBREMESA", CategoriaProdutoEnum.SOBREMESA, 2.0);
        Pedido pedido2 = getPedido(2L, cliente, EstadoPedidoEnum.RECEBIDO, null);
        PedidoProduto pedidoProduto2 = getPedidoProduto(2L, pedido2, produto2, 1);
        pedido2.setPedidosProdutos(List.of(pedidoProduto2));


        Produto produto3 = getProduto(1L, "BEBIDA", "BEBIDA", CategoriaProdutoEnum.BEBIDA, 2.0);
        Pedido pedido3 = getPedido(3L, cliente, EstadoPedidoEnum.RECEBIDO, null);
        PedidoProduto pedidoProduto3 = getPedidoProduto(3L, pedido3, produto3, 1);
        pedido3.setPedidosProdutos(List.of(pedidoProduto3));


        List<Pedido> pedidos = pedidoUseCases.ordenarListarPedidos(List.of(pedido, pedido2, pedido3));

        Assert.assertEquals(pedidos.get(0), pedido);
        Assert.assertEquals(pedidos.get(1), pedido2);
        Assert.assertEquals(pedidos.get(2), pedido3);
    }

    @Test
    public void testOrdenarListarPedidosPorEstado(){
        Cliente cliente = getCliente(1L, "teste", "teste@teste.com", new CPF("12345678909"));

        Produto produto = getProduto(1L, "LANCHE", "LANCHE", CategoriaProdutoEnum.LANCHE, 2.0);
        Pedido pedido = getPedido(1L, cliente, EstadoPedidoEnum.PREPARANDO, null);
        PedidoProduto pedidoProduto = getPedidoProduto(1L, pedido, produto, 1);
        pedido.setPedidosProdutos(List.of(pedidoProduto));


        Produto produto2 = getProduto(1L, "SOBREMESA", "SOBREMESA", CategoriaProdutoEnum.SOBREMESA, 2.0);
        Pedido pedido2 = getPedido(2L, cliente, EstadoPedidoEnum.RECEBIDO, null);
        PedidoProduto pedidoProduto2 = getPedidoProduto(2L, pedido2, produto2, 1);
        pedido2.setPedidosProdutos(List.of(pedidoProduto2));


        Produto produto3 = getProduto(1L, "BEBIDA", "BEBIDA", CategoriaProdutoEnum.BEBIDA, 2.0);
        Pedido pedido3 = getPedido(3L, cliente, EstadoPedidoEnum.PRONTO, null);
        PedidoProduto pedidoProduto3 = getPedidoProduto(3L, pedido3, produto3, 1);
        pedido3.setPedidosProdutos(List.of(pedidoProduto3));


        List<Pedido> pedidos = pedidoUseCases.ordenarListarPedidos(List.of(pedido, pedido2, pedido3));

        Assert.assertEquals(pedidos.get(0), pedido3);
        Assert.assertEquals(pedidos.get(1), pedido);
        Assert.assertEquals(pedidos.get(2), pedido2);
    }

    @Test
    public void testOrdenarListarPedidosPorEstadoEAntiguidade(){
        Cliente cliente = getCliente(1L, "teste", "teste@teste.com", new CPF("12345678909"));

        Produto produto = getProduto(1L, "LANCHE", "LANCHE", CategoriaProdutoEnum.LANCHE, 2.0);
        Pedido pedido = getPedido(1L, cliente, EstadoPedidoEnum.RECEBIDO, null);
        PedidoProduto pedidoProduto = getPedidoProduto(1L, pedido, produto, 1);
        pedido.setPedidosProdutos(List.of(pedidoProduto));


        Produto produto2 = getProduto(1L, "SOBREMESA", "SOBREMESA", CategoriaProdutoEnum.SOBREMESA, 2.0);
        Pedido pedido2 = getPedido(2L, cliente, EstadoPedidoEnum.PRONTO, null);
        PedidoProduto pedidoProduto2 = getPedidoProduto(2L, pedido2, produto2, 1);
        pedido2.setPedidosProdutos(List.of(pedidoProduto2));


        Produto produto3 = getProduto(1L, "BEBIDA", "BEBIDA", CategoriaProdutoEnum.BEBIDA, 2.0);
        Pedido pedido3 = getPedido(3L, cliente, EstadoPedidoEnum.RECEBIDO, null);
        PedidoProduto pedidoProduto3 = getPedidoProduto(3L, pedido3, produto3, 1);
        pedido3.setPedidosProdutos(List.of(pedidoProduto3));

        List<Pedido> pedidos = pedidoUseCases.ordenarListarPedidos(List.of(pedido, pedido2, pedido3));

        Assert.assertEquals(pedidos.get(0), pedido2);
        Assert.assertEquals(pedidos.get(1), pedido);
        Assert.assertEquals(pedidos.get(2), pedido3);
    }

    @Test
    public void testFiltrarPedidos(){
        Cliente cliente = getCliente(1L, "teste", "teste@teste.com", new CPF("12345678909"));

        Produto produto = getProduto(1L, "LANCHE", "LANCHE", CategoriaProdutoEnum.LANCHE, 2.0);
        Pedido pedido = getPedido(1L, cliente, EstadoPedidoEnum.RECEBIDO, null);
        PedidoProduto pedidoProduto = getPedidoProduto(1L, pedido, produto, 1);
        pedido.setPedidosProdutos(List.of(pedidoProduto));


        Produto produto2 = getProduto(1L, "SOBREMESA", "SOBREMESA", CategoriaProdutoEnum.SOBREMESA, 2.0);
        Pedido pedido2 = getPedido(2L, cliente, EstadoPedidoEnum.CANCELADO, null);
        PedidoProduto pedidoProduto2 = getPedidoProduto(2L, pedido2, produto2, 1);
        pedido2.setPedidosProdutos(List.of(pedidoProduto2));


        Produto produto3 = getProduto(1L, "BEBIDA", "BEBIDA", CategoriaProdutoEnum.BEBIDA, 2.0);
        Pedido pedido3 = getPedido(3L, cliente, EstadoPedidoEnum.FINALIZADO, null);
        PedidoProduto pedidoProduto3 = getPedidoProduto(3L, pedido3, produto3, 1);
        pedido3.setPedidosProdutos(List.of(pedidoProduto3));

        List<Pedido> pedidos = pedidoUseCases.filtrarPedidos(List.of(pedido, pedido2, pedido3), List.of(EstadoPedidoEnum.FINALIZADO, EstadoPedidoEnum.CANCELADO));

        Assert.assertEquals(pedidos.get(0), pedido);
        Assert.assertEquals(1, pedidos.size());
    }

    @Test
    public void teslistarPedidos(){
        Cliente cliente = getCliente(1L, "teste", "teste@teste.com", new CPF("12345678909"));

        Produto produto = getProduto(1L, "LANCHE", "LANCHE", CategoriaProdutoEnum.LANCHE, 2.0);
        Pedido pedido = getPedido(1L, cliente, EstadoPedidoEnum.RECEBIDO, null);
        PedidoProduto pedidoProduto = getPedidoProduto(1L, pedido, produto, 1);
        pedido.setPedidosProdutos(List.of(pedidoProduto));


        Produto produto2 = getProduto(1L, "SOBREMESA", "SOBREMESA", CategoriaProdutoEnum.SOBREMESA, 2.0);
        Pedido pedido2 = getPedido(2L, cliente, EstadoPedidoEnum.RECEBIDO, null);
        PedidoProduto pedidoProduto2 = getPedidoProduto(2L, pedido2, produto2, 1);
        pedido2.setPedidosProdutos(List.of(pedidoProduto2));


        Produto produto3 = getProduto(1L, "BEBIDA", "BEBIDA", CategoriaProdutoEnum.BEBIDA, 2.0);
        Pedido pedido3 = getPedido(3L, cliente, EstadoPedidoEnum.FINALIZADO, null);
        PedidoProduto pedidoProduto3 = getPedidoProduto(3L, pedido3, produto3, 1);
        pedido3.setPedidosProdutos(List.of(pedidoProduto3));

        Mockito.doReturn(List.of(pedido3, pedido, pedido2)).when(pedidoUseCases).consultaTodosOsPedidos();

        List<Pedido> pedidos = pedidoUseCases.listarPedidos();

        Assert.assertEquals(pedidos.get(0), pedido);
        Assert.assertEquals(pedidos.get(1), pedido2);
        Assert.assertEquals(2, pedidos.size());

    }

    private PedidoProduto getPedidoProduto(Long id, Pedido pedido, Produto produto, Integer quantidade) {
        return new PedidoProduto(id,  pedido,  produto, quantidade);
    }

    private Pedido getPedido(Long id, Cliente cliente, EstadoPedidoEnum estado, List<PedidoProduto> pedidosProdutos) {
        return new Pedido(id, cliente,  estado, pedidosProdutos);
    }

    private Produto getProduto(Long id, String nome, String descricao, CategoriaProdutoEnum categoria, Double preco) {
        return new Produto(id, nome, descricao, categoria, preco);
    }

    private Cliente getCliente(Long id, String nome, String email, CPF cpf) {
        return new Cliente(id, nome, email, cpf);
    }
}