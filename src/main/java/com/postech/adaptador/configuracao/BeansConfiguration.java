package com.postech.adaptador.configuracao;

import com.postech.nucleo.aplicacao.servico.ClienteServicoImpl;
import com.postech.nucleo.aplicacao.servico.ProdutoServicoImpl;
import com.postech.nucleo.aplicacao.servico.PedidoServicoImpl;
import com.postech.nucleo.dominio.repositorio.ClienteRepositorio;
import com.postech.nucleo.dominio.repositorio.PedidoRepositorio;
import com.postech.nucleo.dominio.repositorio.ProdutoRepositorio;
import com.postech.nucleo.aplicacao.servico.ClienteServico;
import com.postech.nucleo.aplicacao.servico.PedidoServico;
import com.postech.nucleo.aplicacao.servico.ProdutoServico;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Boolean.class)
public class BeansConfiguration {


    @Bean
    ProdutoServico produtoServico(final ProdutoRepositorio produtoRepositorio) {
        return new ProdutoServicoImpl(produtoRepositorio);
    }

    @Bean
    ClienteServico clienteServico(final ClienteRepositorio clienteRepositorio) {
        return new ClienteServicoImpl(clienteRepositorio);
    }

    @Bean
    PedidoServico pedidoServico(final PedidoRepositorio pedidoRepositorio, final ProdutoRepositorio produtoRepositorio, final ClienteRepositorio clienteRepositorio) {
        return new PedidoServicoImpl(pedidoRepositorio, produtoRepositorio, clienteRepositorio);
    }
}
