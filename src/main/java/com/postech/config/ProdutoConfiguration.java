package com.postech.config;

import com.postech.application.gateways.RepositorioDePedidoGateway;
import com.postech.application.usecases.ClienteUseCases;
import com.postech.application.usecases.PagamentoUseCases;
import com.postech.application.usecases.PedidoUseCases;
import com.postech.application.usecases.ProdutoUseCases;
import com.postech.infra.gateways.RepositorioDePedidoGatewayImpl;
import com.postech.infra.mappers.PedidoMapper;
import com.postech.infra.persistence.repositories.PedidoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfiguration {

    @Bean
    PedidoUseCases pedidoUseCases(RepositorioDePedidoGateway repositorioDePedidoGateway, ProdutoUseCases produtoUseCases, ClienteUseCases clienteUseCases, PagamentoUseCases pagamentoUseCases) {
        return new PedidoUseCases(repositorioDePedidoGateway, produtoUseCases, clienteUseCases, pagamentoUseCases);
    }

    @Bean
    RepositorioDePedidoGatewayImpl repositorioDePedidoGateway(PedidoRepository repository, PedidoMapper mapper) {
        return new RepositorioDePedidoGatewayImpl(repository, mapper);
    }

}
