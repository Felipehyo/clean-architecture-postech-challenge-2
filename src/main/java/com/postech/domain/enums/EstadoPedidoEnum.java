package com.postech.domain.enums;

public enum EstadoPedidoEnum {

    RECEBIDO(1),
    PREPARANDO(2),
    PRONTO(3),
    FINALIZADO(4),
    CANCELADO(null);

    final Integer ordem;

    EstadoPedidoEnum(Integer ordem) {
        this.ordem = ordem;
    }

    public Integer getOrdem() {
        return ordem;
    }
}
