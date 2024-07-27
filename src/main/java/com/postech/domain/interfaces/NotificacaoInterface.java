package com.postech.domain.interfaces;

import com.postech.infra.dto.request.NotificacaoPagamentoDTO;

public interface NotificacaoInterface {

    public NotificacaoPagamentoDTO atualizaNotificacaoPagamento(String notificacaoPagamento);
}
