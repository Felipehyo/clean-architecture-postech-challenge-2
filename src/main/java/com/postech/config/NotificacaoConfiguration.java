package com.postech.config;

import com.postech.application.gateways.RepositorioDePagamentoGateway;
import com.postech.application.usecases.NotificacaoUseCases;
import com.postech.application.usecases.PagamentoUseCases;
import com.postech.application.usecases.PedidoUseCases;
import com.postech.domain.interfaces.NotificacaoInterface;
import com.postech.domain.interfaces.PagamentoInterface;
import com.postech.infra.gateways.RepositorioDePagamentoImpl;
import com.postech.infra.mappers.PagamentoMapper;
import com.postech.infra.mercadopago.usecases.MercadoPagoUseCase;
import com.postech.infra.mercadopago.usecases.NotificacaoMercadoPagoUseCase;
import com.postech.infra.persistence.repositories.PagamentoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoConfiguration {

    @Bean
    NotificacaoUseCases notificacaoUseCases(PagamentoUseCases pagamentoUseCases, NotificacaoInterface notificacaoInterface) {
        return new NotificacaoUseCases(pagamentoUseCases, notificacaoInterface);
    }

    @Bean
    NotificacaoInterface pagamentoExterno(){
        return new NotificacaoMercadoPagoUseCase();
    }

}
