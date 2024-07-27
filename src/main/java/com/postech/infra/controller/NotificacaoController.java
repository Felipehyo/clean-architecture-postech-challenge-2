package com.postech.infra.controller;

import com.postech.application.usecases.NotificacaoUseCases;
import com.postech.infra.dto.request.NotificacaoPagamentoDTO;
import com.postech.infra.resource.NotificacaoResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoController implements NotificacaoResource {

    private NotificacaoUseCases useCases;

    public NotificacaoController(NotificacaoUseCases useCases) {
        this.useCases = useCases;
    }

    @Override
    public ResponseEntity<Object> notificaEstadoPagamento(String notificacaoPagamento) {
        NotificacaoPagamentoDTO notificacaoPagamentoDTO = useCases.atualizaNotificacaoPagamento(notificacaoPagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacaoPagamentoDTO);
    }
}
