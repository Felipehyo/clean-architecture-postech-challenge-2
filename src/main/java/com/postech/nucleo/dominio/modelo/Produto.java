package com.postech.nucleo.dominio.modelo;

import com.postech.nucleo.dominio.base.AgregacaoInterface;
import com.postech.nucleo.dominio.base.DominioExcecao;
import com.postech.nucleo.dominio.base.PreocupacaoAssercao;
import com.postech.nucleo.dominio.enums.CategoriaProdutoEnum;
import lombok.Setter;

public class Produto implements AgregacaoInterface {
    private Long id;

    private String nome;

    private String descricao;

    private CategoriaProdutoEnum categoria;

    private Double preco;

    public Produto(Long id, String nome, String descricao, CategoriaProdutoEnum categoria, Double preco) throws DominioExcecao {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        validaEntidade();
    }

    public void validaEntidade() throws DominioExcecao {
        PreocupacaoAssercao.validaArgumentoNaoVazio(nome, "O nome não pode estar vazio!");
        PreocupacaoAssercao.validaArgumentoNaoVazio(descricao, "A descrição não pode ser vazia!");
        PreocupacaoAssercao.validaArgumentoNaoNulo(categoria, "A categoria não pode estar vazia!");
        PreocupacaoAssercao.validaValorPositivo(preco, "O valor do produto não pode ser negativo!");
        PreocupacaoAssercao.validaArgumentoNaoNulo(preco, "O valor do produto não pode ser nulo");
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaProdutoEnum getCategoria() {
        return categoria;
    }

    public Double getPreco() {
        return preco;
    }

}
