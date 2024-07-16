package com.postech.domain.entities;

import com.postech.domain.exceptions.DominioException;
import com.postech.domain.enums.CategoriaProdutoEnum;
import com.postech.domain.utils.ValidacaoUtils;

public class Produto {
    private Long id;

    private String nome;

    private String descricao;

    private CategoriaProdutoEnum categoria;

    private Double preco;

    public Produto(Long id, String nome, String descricao, CategoriaProdutoEnum categoria, Double preco) throws DominioException {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        validaEntidade();
    }

    public void validaEntidade() throws DominioException {
        ValidacaoUtils.validaArgumentoNaoVazio(nome, "O nome não pode estar vazio!");
        ValidacaoUtils.validaArgumentoNaoVazio(descricao, "A descrição não pode ser vazia!");
        ValidacaoUtils.validaArgumentoNaoNulo(categoria, "A categoria não pode estar vazia!");
        ValidacaoUtils.validaValorPositivo(preco, "O valor do produto não pode ser negativo!");
        ValidacaoUtils.validaArgumentoNaoNulo(preco, "O valor do produto não pode ser nulo");
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoria(CategoriaProdutoEnum categoria) {
        this.categoria = categoria;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
