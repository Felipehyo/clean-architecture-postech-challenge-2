package com.postech.infra.gateways;

import com.postech.application.gateways.RepositorioDeProdutoGateway;
import com.postech.domain.entities.Produto;
import com.postech.domain.enums.CategoriaProdutoEnum;
import com.postech.infra.mappers.ProdutoMapper;
import com.postech.infra.persistence.entities.ProdutoEntity;
import com.postech.infra.persistence.repositories.ProdutoRepository;

import java.util.List;
import java.util.Optional;

public class RepositorioDeProdutoGatewayImpl implements RepositorioDeProdutoGateway {

    private final ProdutoRepository repositorio;
    private final ProdutoMapper mapper;

    public RepositorioDeProdutoGatewayImpl(ProdutoRepository repositorio, ProdutoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Produto consultaProdutoPorId(Long id) {
        Optional<ProdutoEntity> entity = repositorio.getProdutoEntityById(id);
        return entity.map(mapper::paraDominio).orElse(null);
    }

    @Override
    public List<Produto> consultaTodosProdutos(CategoriaProdutoEnum categoria) {
        Optional<List<ProdutoEntity>> entity = repositorio.getProdutoEntity(categoria);
        return entity.map(mapper::paraDominioLista).orElse(null);
    }

    @Override
    public List<Produto> consultaProdutosPorCategoria(CategoriaProdutoEnum categoria) {
        Optional<List<ProdutoEntity>> entity = repositorio.getProdutoEntityByCategoria(categoria);
        return entity.map(mapper::paraDominioLista).orElse(null);
    }

    @Override
    public Produto salvaProduto(Produto produto) {
        ProdutoEntity entity = repositorio.save(mapper.paraEntidade(produto));
        return mapper.paraDominio(entity);
    }

    @Override
    public void deletaProdutoPorId(Long id) {
        repositorio.deleteById(id);
    }

}
