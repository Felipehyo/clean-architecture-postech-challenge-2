package com.postech.nucleo.dominio.modelo;

import com.postech.nucleo.dominio.base.AgregacaoInterface;

public class PedidoProduto implements AgregacaoInterface {

    private Long id;

    private Pedido pedido;

    private Produto produto;

    private int quantidade;


    public PedidoProduto(Long id, Pedido pedido, Produto produto, int quantidade) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "PedidoProduto{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}
