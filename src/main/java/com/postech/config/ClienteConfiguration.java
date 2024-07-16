package com.postech.config;

import com.postech.application.gateways.RepositorioDeClienteGateway;
import com.postech.application.usecases.ClienteUseCases;
import com.postech.infra.gateways.RepositorioDeClienteGatewayImpl;
import com.postech.infra.mappers.ClienteMapper;
import com.postech.infra.persistence.repositories.ClienteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteConfiguration {

    @Bean
    ClienteUseCases clienteUseCases(RepositorioDeClienteGateway repositorioDeClienteGateway) {
        return new ClienteUseCases(repositorioDeClienteGateway);
    }

    @Bean
    RepositorioDeClienteGatewayImpl repositorioDeClienteGateway(ClienteRepository repository, ClienteMapper mapper) {
        return new RepositorioDeClienteGatewayImpl(repository, mapper);
    }

}
