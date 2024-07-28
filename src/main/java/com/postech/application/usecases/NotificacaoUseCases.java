package com.postech.application.usecases;

import com.postech.domain.enums.EstadoPagamentoEnum;
import com.postech.domain.interfaces.NotificacaoExternoInterface;
import com.postech.infra.dto.request.NotificacaoPagamentoDTO;

public class NotificacaoUseCases {

    private final PagamentoUseCases pagamentoUseCases;

    private final NotificacaoExternoInterface notificacaoExternoUseCase;

    public NotificacaoUseCases(PagamentoUseCases pagamentoUseCases, NotificacaoExternoInterface notificacaoExternoUseCase) {
        this.pagamentoUseCases = pagamentoUseCases;
        this.notificacaoExternoUseCase = notificacaoExternoUseCase;
    }

    public void atualizaNotificacaoPagamento(String jsonNotificacao) {
        NotificacaoPagamentoDTO notificacaoPagamentoDTO = notificacaoExternoUseCase.geraNotificaoPagamentoDTO(jsonNotificacao);

        if(notificacaoPagamentoDTO.getEstadoPagamento().equals(EstadoPagamentoEnum.PENDENTE_PAGAMENTO)){
            return;
        }

        pagamentoUseCases.atualizaEstadoPagamento(notificacaoPagamentoDTO);
    }
}
