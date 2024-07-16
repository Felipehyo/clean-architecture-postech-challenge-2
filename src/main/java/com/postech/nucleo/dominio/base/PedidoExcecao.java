package com.postech.nucleo.dominio.base;

import com.postech.nucleo.dominio.enums.ErroPedidoEnum;

public class PedidoExcecao extends RuntimeException {

    private ErroPedidoEnum erro;

    public PedidoExcecao(ErroPedidoEnum erro) {
        this.erro = erro;
    }

    public ErroPedidoEnum getErro() {
        return this.erro;
    }

}
