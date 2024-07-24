package com.postech.domain.interfaces;

import com.postech.domain.entities.Cliente;

public interface ClienteServico {

    Cliente buscarPorId(Long id);
    Cliente buscarPorCPF(String cpf);
    Cliente cadastrarCliente(Cliente cliente);
}
