package com.postech.application.usecases;

import com.postech.application.gateways.RepositorioDeProdutoGateway;
import com.postech.domain.exceptions.ProdutoException;
import com.postech.domain.entities.Produto;
import com.postech.domain.enums.CategoriaProdutoEnum;
import com.postech.domain.enums.ErroProdutoEnum;
import com.postech.domain.interfaces.ProdutoServico;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProdutoUseCases implements ProdutoServico {

    private final RepositorioDeProdutoGateway repositorio;

    public ProdutoUseCases(RepositorioDeProdutoGateway repositorio) {
        this.repositorio = repositorio;
    }

    public Produto consultaPorId(Long id) {

        Produto produto = repositorio.consultaProdutoPorId(id);

        if (produto == null) {
            throw new ProdutoException(ErroProdutoEnum.PRODUTO_NAO_ENCONTRADO);
        }

        return produto;
    }

    public List<Produto> consultaTodos(String categoriaProduto) {
        return repositorio.consultaTodosProdutos(CategoriaProdutoEnum.paraEnum(categoriaProduto));
    }


    public List<Produto> consultaPorCategoria(CategoriaProdutoEnum categoriaProduto) {
        return repositorio.consultaProdutosPorCategoria(categoriaProduto);
    }

    public Produto criarProduto(Produto produto) {
        return repositorio.salvaProduto(produto);
    }

    public Produto atualizaProduto(Long id, Produto novoProduto) {

        var produto = repositorio.consultaProdutoPorId(id);

        if (produto == null) {
            throw new ProdutoException(ErroProdutoEnum.PRODUTO_NAO_ENCONTRADO);
        }

        if (!StringUtils.isBlank(novoProduto.getNome())) produto.setNome(novoProduto.getNome());
        if (!StringUtils.isBlank(novoProduto.getDescricao())) produto.setDescricao(novoProduto.getDescricao());
        if (novoProduto.getCategoria() != null) produto.setCategoria(novoProduto.getCategoria());
        if (novoProduto.getPreco() != null) produto.setPreco(novoProduto.getPreco());

        return repositorio.salvaProduto(produto);
    }


    public void deletaPorId(Long id) {

        if (repositorio.consultaProdutoPorId(id) == null) {
            throw new ProdutoException(ErroProdutoEnum.PRODUTO_NAO_ENCONTRADO);
        }

        try {
            repositorio.deletaProdutoPorId(id);
        } catch (Exception ignore) {
            throw new ProdutoException(ErroProdutoEnum.PRODUTO_REFERENCIADO_EM_PEDIDO);
        }

    }

}
