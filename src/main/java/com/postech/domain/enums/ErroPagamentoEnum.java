package com.postech.domain.enums;

public enum ErroPagamentoEnum {

    PAGAMENTOS_NAO_ENCONTRADOS(400, "Nenhum pagamento foi encontrado"),
    PAGAMENTO_NAO_ENCONTRADO(404, "Pagamento com o ID informado não foi encontrado"),
    PAGAMENTO_NAO_ENCONTRADO_POR_ID_PEDIDO(404, "Pagamento com o ID de pedido informado não foi encontrado"),
    ESTADO_INVALIDO(400, "Não foi possivel realizar a transição de estado para o novo estado informado"),
    NAO_IMPLEMENTADO(500, "Funcionalidade não implementada");

    private final Integer httpStatusCode;
    private final String detalhe;

    ErroPagamentoEnum(Integer httpStatusCode, String detalhe) {
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
