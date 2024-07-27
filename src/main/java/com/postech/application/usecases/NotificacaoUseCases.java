package com.postech.application.usecases;

import com.postech.domain.interfaces.NotificacaoInterface;
import com.postech.infra.dto.request.NotificacaoPagamentoDTO;

public class NotificacaoUseCases {

    private PagamentoUseCases pagamentoUseCases;

    private NotificacaoInterface notificacaoExternoUseCase;

    public NotificacaoUseCases(PagamentoUseCases pagamentoUseCases, NotificacaoInterface notificacaoExternoUseCase) {
        this.pagamentoUseCases = pagamentoUseCases;
        this.notificacaoExternoUseCase = notificacaoExternoUseCase;
    }

    public NotificacaoPagamentoDTO atualizaNotificacaoPagamento(String notificacaoPagamento) {
        NotificacaoPagamentoDTO notificacaoPagamentoDTO = notificacaoExternoUseCase.atualizaNotificacaoPagamento(notificacaoPagamento);

        pagamentoUseCases.atualizaEstadoPagamento(notificacaoPagamentoDTO);

        return notificacaoPagamentoDTO;
    }
}
