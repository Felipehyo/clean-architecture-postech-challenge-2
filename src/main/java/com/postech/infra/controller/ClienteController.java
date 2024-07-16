package com.postech.infra.controller;

import com.postech.application.usecases.ClienteUseCases;
import com.postech.domain.entities.Cliente;
import com.postech.infra.dto.request.ClienteRequestDTO;
import com.postech.infra.mappers.ClienteMapper;
import com.postech.infra.resource.ClienteResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteController implements ClienteResource {

    private final ClienteUseCases useCases;
    private final ClienteMapper mapper;

    public ClienteController(ClienteUseCases useCases, ClienteMapper mapper) {
        this.useCases = useCases;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Object> cadastrarCliente(ClienteRequestDTO clienteRequest) {
        Cliente cliente = useCases.cadastrarCliente(mapper.paraDominio(clienteRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.paraDTO(cliente));
    }

    @Override
    public ResponseEntity<Object> consultarCliente(String cpf) {
        Cliente cliente = useCases.buscarPorCPF(cpf);
        return ResponseEntity.ok().body(mapper.paraDTO(cliente));
    }
}