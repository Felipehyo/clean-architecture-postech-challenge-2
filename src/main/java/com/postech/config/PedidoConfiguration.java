package com.postech.config;

import com.postech.application.gateways.RepositorioDePedidoGateway;
import com.postech.application.gateways.RepositorioDeProdutoGateway;
import com.postech.application.usecases.*;
import com.postech.infra.gateways.RepositorioDeProdutoGatewayImpl;
import com.postech.infra.mappers.ProdutoMapper;
import com.postech.infra.persistence.repositories.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfiguration {

    @Bean
    ProdutoUseCases produtoUseCases(RepositorioDeProdutoGateway repositorioDeProdutoGateway) {
        return new ProdutoUseCases(repositorioDeProdutoGateway);
    }

    @Bean
    RepositorioDeProdutoGatewayImpl repositorioDeProdutoGateway(ProdutoRepository repository, ProdutoMapper mapper) {
        return new RepositorioDeProdutoGatewayImpl(repository, mapper);
    }

}
