package com.postech.infra.controller;

import com.postech.application.usecases.ProdutoUseCases;
import com.postech.domain.entities.Produto;
import com.postech.domain.enums.CategoriaProdutoEnum;
import com.postech.infra.dto.request.ProdutoRequestDTO;
import com.postech.infra.mappers.ProdutoMapper;
import com.postech.infra.resource.ProdutoResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoController implements ProdutoResource {

    private final ProdutoUseCases useCases;
    private final ProdutoMapper mapper;

    public ProdutoController(ProdutoUseCases useCases, ProdutoMapper mapper) {
        this.useCases = useCases;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Object> consultaProdutoPorID(Long id) {
        Produto produto = useCases.consultaPorId(id);
        return ResponseEntity.ok().body(mapper.paraDTO(produto));
    }

    @Override
    public ResponseEntity<Object> criarProduto(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = useCases.criarProduto(mapper.paraDominio(produtoRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.paraDTO(produto));
    }

    @Override
    public ResponseEntity<Object> atualizarProduto(Long id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = useCases.atualizaProduto(id, mapper.paraDominio(produtoRequestDTO));
        return ResponseEntity.ok().body(mapper.paraDTO(produto));
    }

    @Override
    public ResponseEntity<Object> listarTodosProdutos(String categoria) {
        List<Produto> produtos = useCases.consultaTodos(categoria);
        return ResponseEntity.ok().body(mapper.paraDTOListaDominio(produtos));
    }

    @Override
    public ResponseEntity<Object> consultaTodosPorCategoria(CategoriaProdutoEnum categoria) {
        List<Produto> produtos = useCases.consultaPorCategoria(categoria);
        return ResponseEntity.ok().body(mapper.paraDTOListaDominio(produtos));
    }

    @Override
    public ResponseEntity<Object> deletarProdutoPorId(Long id) {
        useCases.deletaPorId(id);
        return ResponseEntity.noContent().build();
    }
}
