package com.postech.domain.entities;

import com.postech.domain.exceptions.DominioException;
import com.postech.domain.enums.EstadoPedidoEnum;
import com.postech.domain.utils.ValidacaoUtils;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Long id;
    private Cliente cliente;
    private EstadoPedidoEnum estado;
    private List<PedidoProduto> pedidosProdutos;

    public Pedido( Long id, Cliente cliente, EstadoPedidoEnum estado, List<PedidoProduto> pedidosProdutos) {
        this.id = id;
        this.cliente = cliente;
        this.estado = estado;
        this.pedidosProdutos = pedidosProdutos;
        validaEntidade();
    }


    public void validaEntidade() throws DominioException {
        ValidacaoUtils.validaArgumentoNaoNulo(estado, "O estado do pedido não pode estar vazio!");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoPedidoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedidoEnum estado) {
        this.estado = estado;
    }

    public List<PedidoProduto> getPedidosProdutos() {
        return pedidosProdutos;
    }

    public void setPedidosProdutos(List<PedidoProduto> pedidosProdutos) {
        this.pedidosProdutos = pedidosProdutos;
    }

}
