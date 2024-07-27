package com.postech.infra.mercadopago.usecases;

import com.postech.application.usecases.PagamentoUseCases;
import com.postech.domain.interfaces.NotificacaoInterface;
import com.postech.infra.dto.request.NotificacaoPagamentoDTO;

public class NotificacaoMercadoPagoUseCase implements NotificacaoInterface {

    @Override
    public NotificacaoPagamentoDTO atualizaNotificacaoPagamento(String notificacaoPagamento) {
        NotificacaoPagamentoDTO notificacaoPagamentoDTO = new NotificacaoPagamentoDTO();

        return notificacaoPagamentoDTO;
    }
}
