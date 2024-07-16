package com.postech.domain.exceptions;

import com.postech.domain.enums.ErroProdutoEnum;

public class ProdutoException extends RuntimeException {

    private final ErroProdutoEnum erro;

    public ProdutoException(ErroProdutoEnum erro) {
        this.erro = erro;
    }

    public ErroProdutoEnum getErro() {
        return this.erro;
    }

}
