package com.postech.nucleo.aplicacao.servico;

import com.postech.adaptador.condutor.api.dto.ClienteDTO;
import com.postech.adaptador.condutor.api.entidade.ClienteEntidade;
import com.postech.adaptador.condutor.api.mapeador.MapeadorCliente;
import com.postech.nucleo.dominio.modelo.Cliente;
import com.postech.nucleo.dominio.repositorio.ClienteRepositorio;

public class ClienteServicoImpl implements ClienteServico {

    private final ClienteRepositorio clientRepository;

    public ClienteServicoImpl(ClienteRepositorio clienteRepository) {
        this.clientRepository = clienteRepository;
    }

    @Override
    public ClienteDTO buscarClientePorCPF(String cpf) {
        ClienteEntidade clienteEntidade = clientRepository.buscarClientePorCPF(cpf);
        Cliente cliente = MapeadorCliente.INSTANCIA.paraDominio(clienteEntidade);
        return MapeadorCliente.INSTANCIA.paraDTO(cliente);
    }

    @Override
    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = MapeadorCliente.INSTANCIA.paraDominio(clienteDTO);
        ClienteEntidade clienteEntidade = MapeadorCliente.INSTANCIA.paraEntidade(cliente);
        ClienteEntidade clienteSalvo  = clientRepository.cadastrarCliente(clienteEntidade);
        Cliente clienteDominio = MapeadorCliente.INSTANCIA.paraDominio(clienteSalvo);
        return MapeadorCliente.INSTANCIA.paraDTO(clienteDominio);
    }

}
