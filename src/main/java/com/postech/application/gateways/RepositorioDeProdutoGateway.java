package com.postech.application.gateways;

import com.postech.domain.entities.Produto;
import com.postech.domain.enums.CategoriaProdutoEnum;

import java.util.List;

public interface RepositorioDeProdutoGateway {
    Produto consultaProdutoPorId(Long id);

    List<Produto> consultaTodosProdutos(CategoriaProdutoEnum produtoCategoria);

    List<Produto> consultaProdutosPorCategoria(CategoriaProdutoEnum produtoCategoria);

    Produto salvaProduto(Produto produto);

    void deletaProdutoPorId(Long id);
}
