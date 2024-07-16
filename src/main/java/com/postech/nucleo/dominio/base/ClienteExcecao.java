package com.postech.nucleo.dominio.base;

import com.postech.nucleo.dominio.enums.ErroClienteEnum;

public class ClienteExcecao extends RuntimeException {

    private ErroClienteEnum erro;

    public ClienteExcecao(ErroClienteEnum erro) {
        this.erro = erro;
    }

    public ErroClienteEnum getErro() {
        return this.erro;
    }

}
