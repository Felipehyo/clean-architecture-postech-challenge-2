package com.postech;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Pós Tech - Arquitetura Hexagonal - Lanchonete",
                version = "1.0",
                description = "Aplicação responsável por fornecer o controle de pedidos para atender os clientes de uma lanchonete de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada."
        )
)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
