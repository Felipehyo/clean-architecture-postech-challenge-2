package com.postech.config;

import com.postech.application.usecases.PagamentoUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoConfiguration {

    @Bean
    PagamentoUseCases pagamentoUseCases() {
        return new PagamentoUseCases();
    }
}
