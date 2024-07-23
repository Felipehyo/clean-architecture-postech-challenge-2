package com.postech.infra.gateways;

import com.postech.application.gateways.RepositorioDePagamentoGateway;
import com.postech.domain.entities.Pagamento;
import com.postech.domain.enums.ErroPedidoEnum;
import com.postech.domain.exceptions.PedidoException;
import com.postech.infra.mappers.PagamentoMapper;
import com.postech.infra.persistence.entities.PagamentoEntity;
import com.postech.infra.persistence.repositories.PagamentoRepository;

public class RepositorioDePagamentoImpl implements RepositorioDePagamentoGateway {

    private final PagamentoRepository repositorio;
    private final PagamentoMapper mapper;

    public RepositorioDePagamentoImpl(PagamentoRepository repositorio, PagamentoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Pagamento salvaPagamento(Pagamento pagamento) {
        PagamentoEntity entity = repositorio.save(mapper.paraEntidade(pagamento));
        return mapper.paraDominio(entity);
    }

    @Override
    public Pagamento consultaPagamentoPorIdPedido(Long id) {
        PagamentoEntity pagamentoEntity = repositorio.getPagamentoEntityByPedidoId(id);

        if(pagamentoEntity == null){
            throw new PedidoException(ErroPedidoEnum.PEDIDOS_NAO_ECONTRADOS);
        }

        return mapper.paraDominio(pagamentoEntity);
    }
}
