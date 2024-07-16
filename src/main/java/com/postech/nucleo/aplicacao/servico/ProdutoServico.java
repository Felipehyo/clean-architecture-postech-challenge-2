package com.postech.nucleo.aplicacao.servico;

import com.postech.adaptador.condutor.api.dto.ProdutoDTO;
import com.postech.nucleo.dominio.enums.CategoriaProdutoEnum;

import java.util.List;

public interface ProdutoServico {
    ProdutoDTO consultaProdutoPorId(Long id);
    List<ProdutoDTO> consultaTodosProdutos(String categoriaProduto);
    List<ProdutoDTO> consultaProdutosPorCategoria(CategoriaProdutoEnum categoriaProduto);
    ProdutoDTO criaNovoProduto(ProdutoDTO produtoDTO);
    ProdutoDTO atualizaProduto(Long id, ProdutoDTO produtoDTO);
    void deletaProdutoPorId(Long id);
}
