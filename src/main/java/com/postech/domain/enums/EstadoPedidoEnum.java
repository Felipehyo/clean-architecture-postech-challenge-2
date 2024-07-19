package com.postech.domain.enums;

public enum EstadoPedidoEnum {

    PAGO(3),
    PENDENTE_PAGAMENTO(2),
    RECEBIDO(1),
    PREPARANDO(4),
    PRONTO(5),
    FINALIZADO(6),
    CANCELADO(null);

    final Integer ordem;

    EstadoPedidoEnum(Integer ordem) {
        this.ordem = ordem;
    }

    public Integer getOrdem() {
        return ordem;
    }
}
