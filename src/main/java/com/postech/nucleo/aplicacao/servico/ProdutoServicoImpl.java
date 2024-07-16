package com.postech.nucleo.aplicacao.servico;

import com.postech.adaptador.condutor.api.dto.ProdutoDTO;
import com.postech.adaptador.condutor.api.entidade.ProdutoEntidade;
import com.postech.adaptador.condutor.api.mapeador.MapeadorProduto;
import com.postech.nucleo.dominio.base.ProdutoExcecao;
import com.postech.nucleo.dominio.enums.CategoriaProdutoEnum;
import com.postech.nucleo.dominio.enums.ErroProdutoEnum;
import com.postech.nucleo.dominio.modelo.Produto;
import com.postech.nucleo.dominio.repositorio.ProdutoRepositorio;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProdutoServicoImpl implements ProdutoServico {

    private final ProdutoRepositorio produtoRepositorio;

    public ProdutoServicoImpl(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }

    @Override
    public ProdutoDTO consultaProdutoPorId(Long id) {
        var produtoEntidade = produtoRepositorio.consultaProdutoPorId(id);
        Produto produto = MapeadorProduto.INSTANCIA.paraDominio(produtoEntidade);
        return MapeadorProduto.INSTANCIA.paraDTO(produto);
    }

    @Override
    public List<ProdutoDTO> consultaTodosProdutos(String categoriaProduto) {
        List<Produto> produtos = MapeadorProduto.INSTANCIA.paraDominioLista(produtoRepositorio.consultaTodosProdutos(CategoriaProdutoEnum.paraEnum(categoriaProduto)));
        return MapeadorProduto.INSTANCIA.paraDTOListaDominio(produtos);
    }

    @Override
    public List<ProdutoDTO> consultaProdutosPorCategoria(CategoriaProdutoEnum categoriaProduto) {
        List<Produto> produtos = MapeadorProduto.INSTANCIA.paraDominioLista(produtoRepositorio.consultaProdutosPorCategoria(categoriaProduto));
        return MapeadorProduto.INSTANCIA.paraDTOListaDominio(produtos);
    }

    @Override
    public ProdutoDTO criaNovoProduto(ProdutoDTO produtoDTO) {

        ProdutoDTO respostaProduto;

        try {
            Produto produto = MapeadorProduto.INSTANCIA.paraDominio(produtoDTO);
            ProdutoEntidade produtoSalvo = produtoRepositorio.criaNovoProduto(MapeadorProduto.INSTANCIA.paraEntidade(produto));
            Produto produtoDominioSalvo = MapeadorProduto.INSTANCIA.paraDominio(produtoSalvo);
            respostaProduto = MapeadorProduto.INSTANCIA.paraDTO(produtoDominioSalvo);
        } catch (Exception ignore) {
            throw new ProdutoExcecao(ErroProdutoEnum.PRODUTO_NAO_ENCONTRADO);
        }

        return respostaProduto;
    }

    @Override
    public ProdutoDTO atualizaProduto(Long id, ProdutoDTO produtoDTO) {

        var produto = produtoRepositorio.consultaProdutoPorId(id);

        if (!StringUtils.isBlank(produtoDTO.getNome())) produto.setNome(produtoDTO.getNome());
        if (!StringUtils.isBlank(produtoDTO.getDescricao())) produto.setDescricao(produtoDTO.getDescricao());
        if (produtoDTO.getCategoria() != null) produto.setCategoria(produtoDTO.getCategoria());
        if (produtoDTO.getPreco() != null) produto.setPreco(produtoDTO.getPreco());

        var produtoSalvo = produtoRepositorio.salvaProduto(produto);

        return MapeadorProduto.INSTANCIA.paraDTO(MapeadorProduto.INSTANCIA.paraDominio(produtoSalvo));
    }

    @Override
    public void deletaProdutoPorId(Long id) {

        produtoRepositorio.consultaProdutoPorId(id);

        try {
            produtoRepositorio.deletaProdutoPorId(id);
        } catch (Exception ignore) {
            throw new ProdutoExcecao(ErroProdutoEnum.PRODUTO_REFERENCIADO_OUTRA_TABELA);
        }

    }
}
