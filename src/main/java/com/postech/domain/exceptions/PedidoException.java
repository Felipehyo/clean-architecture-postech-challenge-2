package com.postech.domain.exceptions;

import com.postech.domain.enums.ErroPedidoEnum;

public class PedidoException extends RuntimeException {

    private final ErroPedidoEnum erro;

    public PedidoException(ErroPedidoEnum erro) {
        this.erro = erro;
    }

    public ErroPedidoEnum getErro() {
        return this.erro;
    }

}
