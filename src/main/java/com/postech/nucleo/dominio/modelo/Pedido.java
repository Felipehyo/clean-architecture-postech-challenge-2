package com.postech.nucleo.dominio.modelo;

import com.postech.nucleo.dominio.base.AgregacaoInterface;
import com.postech.nucleo.dominio.base.DominioExcecao;
import com.postech.nucleo.dominio.base.PreocupacaoAssercao;
import com.postech.nucleo.dominio.enums.EstadoPedidoEnum;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements AgregacaoInterface {

    private Long id;
    private Cliente cliente;
    private EstadoPedidoEnum estado;
    private List<PedidoProduto> pedidosProdutos;

    public Pedido(Long id, Cliente cliente, EstadoPedidoEnum estado, List<PedidoProduto> pedidosProdutos) {
        this.id = id;
        this.cliente = cliente;
        this.estado = estado;
        this.pedidosProdutos = pedidosProdutos;
        validaEntidade();
    }
    public void validaEntidade() throws DominioExcecao {
        PreocupacaoAssercao.validaArgumentoNaoNulo(estado, "O estado do pedido não pode estar vazio!");
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

    public void adicionaPedidoProduto(PedidoProduto pedidoProduto){
        if(pedidosProdutos == null){
            pedidosProdutos = new ArrayList<>();
        }
        pedidosProdutos.add(pedidoProduto);
    }
}
