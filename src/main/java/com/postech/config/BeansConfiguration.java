package com.postech.config;

import com.postech.application.gateways.RepositorioDeClienteGateway;
import com.postech.application.gateways.RepositorioDePedidoGateway;
import com.postech.application.gateways.RepositorioDeProdutoGateway;
import com.postech.application.usecases.ClienteUseCases;
import com.postech.application.usecases.PedidoUseCases;
import com.postech.application.usecases.ProdutoUseCases;
import com.postech.infra.gateways.RepositorioDeClienteGatewayImpl;
import com.postech.infra.gateways.RepositorioDePedidoGatewayImpl;
import com.postech.infra.gateways.RepositorioDeProdutoGatewayImpl;
import com.postech.infra.mappers.ClienteMapper;
import com.postech.infra.mappers.PedidoMapper;
import com.postech.infra.mappers.ProdutoMapper;
import com.postech.infra.persistence.repositories.ClienteRepository;
import com.postech.infra.persistence.repositories.PedidoRepository;
import com.postech.infra.persistence.repositories.ProdutoRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = Boolean.class)
@EnableJpaRepositories(basePackages = "com.postech.infra.persistence.repositories")
@EntityScan(basePackages = "com.postech.infra.persistence.entities")
public class BeansConfiguration {

    @Bean
    ClienteUseCases clienteUseCases(RepositorioDeClienteGateway repositorioDeClienteGateway) {
        return new ClienteUseCases(repositorioDeClienteGateway);
    }

    @Bean
    RepositorioDeClienteGatewayImpl repositorioDeClienteGateway(ClienteRepository repository, ClienteMapper mapper) {
        return new RepositorioDeClienteGatewayImpl(repository, mapper);
    }

    @Bean
    ProdutoUseCases produtoUseCases(RepositorioDeProdutoGateway repositorioDeProdutoGateway) {
        return new ProdutoUseCases(repositorioDeProdutoGateway);
    }

    @Bean
    RepositorioDeProdutoGatewayImpl repositorioDeProdutoGateway(ProdutoRepository repository, ProdutoMapper mapper) {
        return new RepositorioDeProdutoGatewayImpl(repository, mapper);
    }

    @Bean
    PedidoUseCases pedidoUseCases(RepositorioDePedidoGateway repositorioDePedidoGateway, ProdutoUseCases produtoUseCases, ClienteUseCases clienteUseCases) {
        return new PedidoUseCases(repositorioDePedidoGateway, produtoUseCases, clienteUseCases);
    }

    @Bean
    RepositorioDePedidoGatewayImpl repositorioDePedidoGateway(PedidoRepository repository, PedidoMapper mapper) {
        return new RepositorioDePedidoGatewayImpl(repository, mapper);
    }

}
