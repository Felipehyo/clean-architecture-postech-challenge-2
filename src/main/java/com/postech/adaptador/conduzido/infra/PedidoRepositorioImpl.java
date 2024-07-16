package com.postech.adaptador.conduzido.infra;

import com.postech.adaptador.condutor.api.entidade.PedidoEntidade;
import com.postech.nucleo.dominio.base.PedidoExcecao;
import com.postech.nucleo.dominio.enums.ErroPedidoEnum;
import com.postech.nucleo.dominio.repositorio.PedidoRepositorio;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoRepositorioImpl implements PedidoRepositorio {

    private final SpringPedidoRepositorio springPedidoRepositorio;

    public PedidoRepositorioImpl(SpringPedidoRepositorio springPedidoRepositorio) {
        this.springPedidoRepositorio = springPedidoRepositorio;
    }

    @Override
    public PedidoEntidade consultaPedidoPorId(Long id) {

        var pedido = springPedidoRepositorio.getPedidoEntidadeById(id);

        if (pedido.isEmpty()) {
            throw new PedidoExcecao(ErroPedidoEnum.PEDIDO_NAO_ENCONTRADO);
        }

        return pedido.get();
    }

    @Override
    public PedidoEntidade salvaPedido(PedidoEntidade pedidoEntidade) {
        return springPedidoRepositorio.save(pedidoEntidade);
    }

    @Override
    public void deletaPedido(Long id) {
        springPedidoRepositorio.deleteById(id);
    }

    @Override
    public List<PedidoEntidade> pegaTodosProdutos(){
        return springPedidoRepositorio.findAll();
    }

}
