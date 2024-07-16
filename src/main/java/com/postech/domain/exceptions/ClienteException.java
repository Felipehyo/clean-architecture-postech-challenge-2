package com.postech.domain.exceptions;

import com.postech.domain.enums.ErroClienteEnum;

public class ClienteException extends RuntimeException {

    private final ErroClienteEnum erro;

    public ClienteException(ErroClienteEnum erro) {
        this.erro = erro;
    }

    public ErroClienteEnum getErro() {
        return this.erro;
    }

}
