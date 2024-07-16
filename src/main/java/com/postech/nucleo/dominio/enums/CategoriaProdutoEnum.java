package com.postech.nucleo.dominio.enums;

import com.postech.nucleo.dominio.base.ProdutoExcecao;

public enum CategoriaProdutoEnum {
    LANCHE,
    ACOMPANHAMENTO,
    BEBIDA,
    SOBREMESA;

    public static CategoriaProdutoEnum paraEnum(String valor) {
        if (valor == null) return null;
        try {
            return CategoriaProdutoEnum.valueOf(valor);
        } catch (IllegalArgumentException e) {
            throw new ProdutoExcecao(ErroProdutoEnum.CATEGORIA_INVALIDA);
        }
    }

}
