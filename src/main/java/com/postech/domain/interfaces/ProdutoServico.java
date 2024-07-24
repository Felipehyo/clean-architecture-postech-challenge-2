package com.postech.domain.interfaces;


import com.postech.domain.entities.Produto;
import com.postech.domain.enums.CategoriaProdutoEnum;

import java.util.List;

public interface ProdutoServico {

    Produto consultaPorId(Long id);

    List<Produto> consultaTodos(String categoriaProduto);

    List<Produto> consultaPorCategoria(CategoriaProdutoEnum categoriaProduto);

    Produto criarProduto(Produto produto);

    Produto atualizaProduto(Long id, Produto novoProduto);

    void deletaPorId(Long id);
}
