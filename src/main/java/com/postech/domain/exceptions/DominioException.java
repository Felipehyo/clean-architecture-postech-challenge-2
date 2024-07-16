package com.postech.domain.exceptions;

public class DominioException extends RuntimeException {

    public DominioException() {
    }

    public DominioException(String mensagem) {
        super(mensagem);
    }

    public DominioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
