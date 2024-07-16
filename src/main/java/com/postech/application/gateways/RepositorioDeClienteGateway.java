package com.postech.application.gateways;

import com.postech.domain.entities.Cliente;

public interface RepositorioDeClienteGateway {
    Cliente buscarPorCPF(String cpf);

    Cliente cadastrarCliente(Cliente cliente);

    Cliente buscarPorId(Long id);
}
