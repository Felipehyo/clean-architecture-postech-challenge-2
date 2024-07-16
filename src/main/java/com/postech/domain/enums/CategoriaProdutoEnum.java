package com.postech.domain.enums;

import com.postech.domain.exceptions.ProdutoException;

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
            throw new ProdutoException(ErroProdutoEnum.CATEGORIA_INVALIDA);
        }
    }

}
