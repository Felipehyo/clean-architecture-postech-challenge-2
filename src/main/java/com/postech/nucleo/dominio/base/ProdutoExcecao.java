package com.postech.nucleo.dominio.base;

import com.postech.nucleo.dominio.enums.ErroProdutoEnum;

public class ProdutoExcecao extends RuntimeException {

    private ErroProdutoEnum erro;

    public ProdutoExcecao(ErroProdutoEnum erro) {
        this.erro = erro;
    }

    public ErroProdutoEnum getErro() {
        return this.erro;
    }

}
