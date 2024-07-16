package com.postech.nucleo.dominio.enums;

public enum ErroClienteEnum {
    CLIENTE_JA_CADASTRADO(400, "Cliente com CPF informado já se encontra cadastrado"),
    CLIENTE_CPF_NAO_ENCONTRADO(404, "Cliente com CPF informado não encontrado"),
    CLIENTE_ID_NAO_ENCONTRADO(404, "Cliente com Id informado não encontrado");

    private Integer httpStatusCode;
    private String detalhe;

    ErroClienteEnum(Integer httpStatusCode, String detalhe) {
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
