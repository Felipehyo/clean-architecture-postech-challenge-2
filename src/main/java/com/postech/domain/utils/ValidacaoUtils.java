package com.postech.domain.utils;

import com.postech.domain.exceptions.DominioException;

public class ValidacaoUtils {

    public static void validaValorPositivo(Double valor, String mensagem) {
        if (valor < 0.0) {
            throw new DominioException(mensagem);
        }
    }

    /**
     * Validação de string se está vazia
     *
     * @param stringValor
     * @param mensagem
     * @throws DominioException
     */
    public static void validaArgumentoNaoVazio(String stringValor, String mensagem) throws DominioException {
        if (stringValor == null || stringValor.trim().isEmpty()) {
            throw new DominioException(mensagem);
        }
    }

    /**
     * Valida se objeto é nulo
     *
     * @param objeto
     * @param mensagem
     * @throws DominioException
     */
    public static void validaArgumentoNaoNulo(Object objeto, String mensagem) throws DominioException {
        if (objeto == null) {
            throw new DominioException(mensagem);
        }
    }

}
