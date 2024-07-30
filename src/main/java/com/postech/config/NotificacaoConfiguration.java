package com.postech.config;

import com.postech.application.usecases.NotificacaoUseCases;
import com.postech.application.usecases.PagamentoUseCases;
import com.postech.domain.interfaces.NotificacaoExternoInterface;
import com.postech.infra.mercadopago.usecases.NotificacaoMercadoPagoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoConfiguration {

    @Bean
    NotificacaoUseCases notificacaoUseCases(PagamentoUseCases pagamentoUseCases, NotificacaoExternoInterface notificacaoInterface) {
        return new NotificacaoUseCases(pagamentoUseCases, notificacaoInterface);
    }

    @Bean
    NotificacaoExternoInterface pagamentoExterno(){
        return new NotificacaoMercadoPagoUseCase();
    }

}
