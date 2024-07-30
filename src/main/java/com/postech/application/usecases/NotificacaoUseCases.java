package com.postech.application.usecases;

import com.postech.domain.enums.EstadoPagamentoEnum;
import com.postech.domain.interfaces.NotificacaoExternoInterface;
import com.postech.application.utils.NotificacaoPagamento;

public class NotificacaoUseCases {

    private final PagamentoUseCases pagamentoUseCases;

    private final NotificacaoExternoInterface notificacaoExternoUseCase;

    public NotificacaoUseCases(PagamentoUseCases pagamentoUseCases, NotificacaoExternoInterface notificacaoExternoUseCase) {
        this.pagamentoUseCases = pagamentoUseCases;
        this.notificacaoExternoUseCase = notificacaoExternoUseCase;
    }

    public void atualizaNotificacaoPagamento(String jsonNotificacao) {
        NotificacaoPagamento notificacaoPagamento = notificacaoExternoUseCase.geraNotificaoPagamento(jsonNotificacao);

        if(notificacaoPagamento.getEstadoPagamento().equals(EstadoPagamentoEnum.PENDENTE_PAGAMENTO)){
            return;
        }

        pagamentoUseCases.atualizaEstadoPagamento(notificacaoPagamento);
    }
}
