//package com.postech.config;
//
//import com.postech.application.gateways.RepositorioDeCliente;
//import com.postech.application.usecases.ClienteUseCases;
//import com.postech.infra.gateways.RepositorioDeClienteJpaImpl;
//import com.postech.infra.mappers.ClienteMapper;
//import com.postech.infra.persistence.repositories.ClienteRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ComponentScan(basePackageClasses = Boolean.class)
//public class UsuarioConfiguration {
//
//    @Bean
//    ClienteUseCases clienteUseCases(RepositorioDeCliente repositorioDeCliente) {
//        return new ClienteUseCases(repositorioDeCliente);
//    }
//
//    @Bean
//    RepositorioDeClienteJpaImpl repositorioDeClienteJpa(ClienteRepository clienteRepository, ClienteMapper mapper) {
//        return new RepositorioDeClienteJpaImpl(clienteRepository, mapper);
//    }
//
//}
