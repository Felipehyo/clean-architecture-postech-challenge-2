package com.postech.infra.controller;

import com.postech.application.usecases.NotificacaoUseCases;
import com.postech.infra.resource.NotificacaoResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoController implements NotificacaoResource {

    private final NotificacaoUseCases useCases;

    public NotificacaoController(NotificacaoUseCases useCases) {
        this.useCases = useCases;
    }

    @Override
    public ResponseEntity<Object> notificaEstadoPagamentoMercadoPago(String notificacaoPagamento) {
        useCases.atualizaNotificacaoPagamento(notificacaoPagamento);
        return ResponseEntity.ok().body("Notificação do estado do pagamento recebida com sucesso");
    }
}
