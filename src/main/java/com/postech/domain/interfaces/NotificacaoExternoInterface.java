package com.postech.domain.interfaces;

import com.postech.infra.dto.request.NotificacaoPagamentoDTO;

public interface NotificacaoExternoInterface {

    public NotificacaoPagamentoDTO geraNotificaoPagamentoDTO(String jsonNotificacao);
}
