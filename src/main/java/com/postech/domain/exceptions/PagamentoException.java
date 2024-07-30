package com.postech.domain.exceptions;

import com.postech.domain.enums.ErroPagamentoEnum;

public class PagamentoException extends RuntimeException{

    private final ErroPagamentoEnum erro;

    public PagamentoException(ErroPagamentoEnum erro) {
        this.erro = erro;
    }

    public ErroPagamentoEnum getErro() {
        return this.erro;
    }

}
