package com.postech.infra.gateways;

import com.postech.application.gateways.RepositorioDePedidoGateway;
import com.postech.domain.entities.Pedido;
import com.postech.infra.mappers.PedidoMapper;
import com.postech.infra.persistence.entities.PedidoEntity;
import com.postech.infra.persistence.repositories.PedidoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositorioDePedidoGatewayImpl implements RepositorioDePedidoGateway {

    private final PedidoRepository repositorio;
    private final PedidoMapper mapper;

    public RepositorioDePedidoGatewayImpl(PedidoRepository repositorio, PedidoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Pedido consultaPedidoPorId(Long id) {
        Optional<PedidoEntity> entity = repositorio.getPedidoEntityById(id);
        return entity.map(mapper::paraDominio).orElse(null);
    }

    @Override
    public Pedido salvaPedido(Pedido pedido) {
        PedidoEntity entity = repositorio.save(mapper.paraEntidade(pedido));
        return mapper.paraDominio(entity);
    }

    @Override
    public void deletaPedido(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<Pedido> buscaTodosPedidos() {
        List<PedidoEntity> entity = repositorio.findAll();
        return Optional.of(entity).orElse(null).stream().map(mapper::paraDominio).collect(Collectors.toList());
    }

}
