package com.postech.infra.mercadopago.config;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MercadoPagoConfiguration {

    public MercadoPagoConfiguration() {
        MercadoPagoConfig.setAccessToken("APP_USR-2734679388176382-071714-c55e98f1f9f0665bbd25ca3993c17293-483752154");
    }

}
