package com.postech.application.usecases;

import com.postech.application.gateways.RepositorioDeClienteGateway;
import com.postech.domain.exceptions.ClienteException;
import com.postech.domain.entities.Cliente;
import com.postech.domain.enums.ErroClienteEnum;
import com.postech.domain.interfaces.ClienteServico;

public class ClienteUseCases implements ClienteServico {

    private final RepositorioDeClienteGateway repositorio;

    public ClienteUseCases(RepositorioDeClienteGateway repositorio) {
        this.repositorio = repositorio;
    }

    public Cliente buscarPorId(Long id) {

        Cliente cliente = repositorio.buscarPorId(id);

        if (cliente == null) {
            throw new ClienteException(ErroClienteEnum.CLIENTE_ID_NAO_ENCONTRADO);
        }

        return cliente;
    }

    public Cliente buscarPorCPF(String cpf) {

        Cliente cliente = repositorio.buscarPorCPF(cpf);

        if (cliente == null) {
            throw new ClienteException(ErroClienteEnum.CLIENTE_CPF_NAO_ENCONTRADO);
        }

        return cliente;

    }

    public Cliente cadastrarCliente(Cliente cliente) {

        if (repositorio.buscarPorCPF(cliente.getNumeroCpf()) != null) {
            throw new ClienteException(ErroClienteEnum.CLIENTE_JA_CADASTRADO);
        }

        return repositorio.cadastrarCliente(cliente);
    }

}
