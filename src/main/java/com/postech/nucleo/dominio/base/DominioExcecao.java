package com.postech.nucleo.dominio.base;

public class DominioExcecao extends RuntimeException {

    public DominioExcecao() {
    }

    public DominioExcecao(String mensagem) {
        super(mensagem);
    }

    public DominioExcecao(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
