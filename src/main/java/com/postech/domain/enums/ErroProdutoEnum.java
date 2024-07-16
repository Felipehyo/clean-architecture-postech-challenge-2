package com.postech.domain.enums;

public enum ErroProdutoEnum {
    PRODUTO_NAO_ENCONTRADO(404, "Produto não encontrado"),
    PRODUTO_REFERENCIADO_EM_PEDIDO(400, "Produto não pode ser deletado pois esta esta sendo referenciado em algum pedido"),
    CATEGORIA_INVALIDA(404, "A categoria informada não é válida");

    private final Integer httpStatusCode;
    private final String detalhe;

    ErroProdutoEnum(Integer httpStatusCode, String detalhe) {
        this.httpStatusCode = httpStatusCode;
        this.detalhe = detalhe;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getDetalhe() {
        return detalhe;
    }
}
