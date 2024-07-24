package com.postech.config;

import com.postech.application.gateways.RepositorioDePagamentoGateway;
import com.postech.application.usecases.PagamentoUseCases;
import com.postech.application.usecases.PedidoUseCases;
import com.postech.infra.gateways.RepositorioDePagamentoImpl;
import com.postech.infra.mappers.PagamentoMapper;
import com.postech.infra.persistence.repositories.PagamentoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoConfiguration {

    @Bean
    PagamentoUseCases pagamentoUseCases(RepositorioDePagamentoGateway repositorio, PedidoUseCases pedidoUseCases) {
        return new PagamentoUseCases(pedidoUseCases, repositorio);
    }

    @Bean
    RepositorioDePagamentoImpl repositorioDePagamentoGateway(PagamentoRepository repository, PagamentoMapper mapper) {
        return new RepositorioDePagamentoImpl(repository, mapper);
    }

}
