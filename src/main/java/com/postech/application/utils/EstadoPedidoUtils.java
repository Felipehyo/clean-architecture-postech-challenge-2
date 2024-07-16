package com.postech.application.utils;

import com.postech.domain.exceptions.PedidoException;
import com.postech.domain.enums.ErroPedidoEnum;
import com.postech.domain.enums.EstadoPedidoEnum;

public class EstadoPedidoUtils {

    /**
     * Este método verifica se a transição de estado informada é permitida
     *
     * @param estadoAtual
     * @param novoEstado
     * @return
     */
    public static boolean validaEstado(EstadoPedidoEnum estadoAtual, EstadoPedidoEnum novoEstado) {
        return switch (estadoAtual) {
            case PENDENTE_PAGAMENTO -> novoEstado == EstadoPedidoEnum.PAGO || novoEstado == EstadoPedidoEnum.CANCELADO;
            case PAGO -> novoEstado == EstadoPedidoEnum.RECEBIDO || novoEstado == EstadoPedidoEnum.CANCELADO;
            case RECEBIDO -> novoEstado == EstadoPedidoEnum.PREPARANDO || novoEstado == EstadoPedidoEnum.CANCELADO;
            case PREPARANDO -> novoEstado == EstadoPedidoEnum.PRONTO || novoEstado == EstadoPedidoEnum.CANCELADO;
            case PRONTO -> novoEstado == EstadoPedidoEnum.FINALIZADO || novoEstado == EstadoPedidoEnum.CANCELADO;
            case FINALIZADO, CANCELADO ->
                    throw new PedidoException(ErroPedidoEnum.ESTADO_INVALIDO); // Não pode mudar de FINALIZADO ou CANCELADO
        };
    }

}
