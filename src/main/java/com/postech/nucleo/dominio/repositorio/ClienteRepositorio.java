package com.postech.nucleo.dominio.repositorio;

import com.postech.adaptador.condutor.api.entidade.ClienteEntidade;

public interface ClienteRepositorio {
    ClienteEntidade buscarClientePorCPF(String cpf);
    ClienteEntidade cadastrarCliente(ClienteEntidade clienteEntidade);
    ClienteEntidade buscarClientePorId(Long id);
}
