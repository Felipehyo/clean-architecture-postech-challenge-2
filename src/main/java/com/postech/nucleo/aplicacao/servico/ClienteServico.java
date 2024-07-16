package com.postech.nucleo.aplicacao.servico;

import com.postech.adaptador.condutor.api.dto.ClienteDTO;

public interface ClienteServico {
    ClienteDTO buscarClientePorCPF(String cpf);
    ClienteDTO cadastrarCliente(ClienteDTO clienteDTO);
}
