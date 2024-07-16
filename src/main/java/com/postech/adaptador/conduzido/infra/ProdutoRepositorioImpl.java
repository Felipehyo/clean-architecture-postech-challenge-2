package com.postech.adaptador.conduzido.infra;

import com.postech.adaptador.condutor.api.entidade.ProdutoEntidade;
import com.postech.nucleo.dominio.base.ProdutoExcecao;
import com.postech.nucleo.dominio.enums.CategoriaProdutoEnum;
import com.postech.nucleo.dominio.enums.ErroProdutoEnum;
import com.postech.nucleo.dominio.repositorio.ProdutoRepositorio;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoRepositorioImpl implements ProdutoRepositorio {

    private final SpringProdutoRepositorio springProdutoRepositorio;

    public ProdutoRepositorioImpl(SpringProdutoRepositorio springProdutoRepositorio) {
        this.springProdutoRepositorio = springProdutoRepositorio;
    }

    @Override
    public ProdutoEntidade consultaProdutoPorId(Long id) {
        var produto = springProdutoRepositorio.getProdutoEntidadeById(id);

        if (produto.isEmpty()) {
            throw new ProdutoExcecao(ErroProdutoEnum.PRODUTO_NAO_ENCONTRADO);
        }

        return produto.get();
    }

    @Override
    public List<ProdutoEntidade> consultaTodosProdutos(CategoriaProdutoEnum produtoCategoria) {
        return springProdutoRepositorio.getProdutoEntidade(produtoCategoria)
                .orElseThrow(() -> new ProdutoExcecao(ErroProdutoEnum.PRODUTOS_NAO_ENCONTRADOS));
    }

    @Override
    public List<ProdutoEntidade> consultaProdutosPorCategoria(CategoriaProdutoEnum produtoCategoria) {
        return springProdutoRepositorio.getProdutoEntidadeByCategoria(produtoCategoria)
                .orElseThrow(() -> new ProdutoExcecao(ErroProdutoEnum.PRODUTO_NAO_ENCONTRADO));
    }

    @Override
    public ProdutoEntidade criaNovoProduto(ProdutoEntidade produtoEntidade) {
        return springProdutoRepositorio.save(produtoEntidade);
    }

    @Override
    public ProdutoEntidade salvaProduto(ProdutoEntidade produtoEntidade) {
        return springProdutoRepositorio.save(produtoEntidade);
    }

    @Override
    public void deletaProdutoPorId(Long id) {
        springProdutoRepositorio.deleteById(id);
    }

}
