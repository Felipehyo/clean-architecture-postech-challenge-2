package com.postech.domain.interfaces;

import com.postech.application.utils.NotificacaoPagamento;

public interface NotificacaoExternoInterface {

    public NotificacaoPagamento geraNotificaoPagamento(String jsonNotificacao);
}
