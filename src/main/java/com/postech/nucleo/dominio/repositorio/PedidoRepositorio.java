package com.postech.nucleo.dominio.repositorio;

import com.postech.adaptador.condutor.api.entidade.PedidoEntidade;

import java.util.List;

public interface PedidoRepositorio {
    PedidoEntidade consultaPedidoPorId(Long id);
    PedidoEntidade salvaPedido(PedidoEntidade pedidoEntidade);
    void deletaPedido(Long id);

    List<PedidoEntidade> pegaTodosProdutos();

}
