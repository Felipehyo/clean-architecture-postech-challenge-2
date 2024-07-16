package com.postech.infra.gateways;

import com.postech.application.gateways.RepositorioDeClienteGateway;
import com.postech.domain.entities.Cliente;
import com.postech.infra.mappers.ClienteMapper;
import com.postech.infra.persistence.entities.ClienteEntity;
import com.postech.infra.persistence.repositories.ClienteRepository;

import java.util.Optional;

public class RepositorioDeClienteGatewayImpl implements RepositorioDeClienteGateway {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper mapper;

    public RepositorioDeClienteGatewayImpl(ClienteRepository clienteRepository, ClienteMapper mapper) {
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        ClienteEntity entity = mapper.paraEntidade(cliente);
        return mapper.paraDominio(clienteRepository.save(entity));
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<ClienteEntity> entity = clienteRepository.getClienteEntityById(id);
        return entity.map(mapper::paraDominio).orElse(null);
    }

    @Override
    public Cliente buscarPorCPF(String cpf) {
        Optional<ClienteEntity> entity = clienteRepository.getClienteEntityByCpf(cpf);
        return entity.map(mapper::paraDominio).orElse(null);
    }

}
