package com.postech.nucleo.dominio.enums;

public enum ErroProdutoEnum {
    PRODUTO_NAO_ENCONTRADO(404, "Produto não encontrado"),
    PRODUTO_REFERENCIADO_OUTRA_TABELA(400, "Produto não pode ser deletado pois esta esta sendo referenciado em outra tabela"),
    CATEGORIA_INVALIDA(404, "A categoria informada não é válida"),
    PRODUTOS_NAO_ENCONTRADOS(404, "Não foram encontrados produtos que correspondem aos dados informados");

    private Integer httpStatusCode;
    private String detalhe;

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
