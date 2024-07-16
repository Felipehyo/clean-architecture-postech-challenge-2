package com.postech.nucleo.dominio.base;

public class PreocupacaoAssercao {

    /**
     * Validação de tamanho máximo de string
     * @param stringValor
     * @param maximo
     * @param mensagem
     * @throws DominioExcecao
     */
    public static void validaTamanhoArgumento(String stringValor, int maximo, String mensagem) throws DominioExcecao {
        int tamanho = stringValor.trim().length();
        if(tamanho > maximo){
            throw new DominioExcecao(mensagem);
        }
    }

    /**
     * Validação de tamanho minimo e máximo (precisa estar entre os dois)
     * @param stringValor
     * @param minimo
     * @param maximo
     * @param mensagem
     * @throws DominioExcecao
     */
    public static void validaTamanhoArgumento(String stringValor, int minimo, int maximo, String mensagem) throws DominioExcecao {
        int tamanho = stringValor.trim().length();
        if(tamanho < minimo || tamanho > maximo){
            throw new DominioExcecao(mensagem);
        }
    }

    public static void validaValorPositivo(Double valor,String mensagem){
        if(valor < 0.0){
            throw new DominioExcecao(mensagem);
        }
    }

    /**
     * Validação de string se está vazia
     * @param stringValor
     * @param mensagem
     * @throws DominioExcecao
     */
    public static void validaArgumentoNaoVazio(String stringValor, String mensagem) throws DominioExcecao {
        if(stringValor == null || stringValor.trim().isEmpty()){
            throw new DominioExcecao(mensagem);
        }
    }

    /**
     * Valida se objeto é nulo
     * @param objeto
     * @param mensagem
     * @throws DominioExcecao
     */
    public static void validaArgumentoNaoNulo(Object objeto, String mensagem) throws DominioExcecao {
        if(objeto == null){
            throw new DominioExcecao(mensagem);
        }
    }

}
