package com.postech.adaptador.conduzido.infra;

import com.postech.adaptador.condutor.api.entidade.ClienteEntidade;
import com.postech.nucleo.dominio.base.ClienteExcecao;
import com.postech.nucleo.dominio.enums.ErroClienteEnum;
import com.postech.nucleo.dominio.repositorio.ClienteRepositorio;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepositorioImpl implements ClienteRepositorio {

    private final SpringClienteRepositorio springClienteRepositorio;

    public ClienteRepositorioImpl(SpringClienteRepositorio springClienteRepositorio) {
        this.springClienteRepositorio = springClienteRepositorio;
    }

    @Override
    public ClienteEntidade cadastrarCliente(ClienteEntidade clienteEntidade) {

        var client = springClienteRepositorio.getClienteEntidadeByCpf(clienteEntidade.getCpf());

        if (client.isPresent()) {
            throw new ClienteExcecao(ErroClienteEnum.CLIENTE_JA_CADASTRADO);
        }

        return springClienteRepositorio.save(clienteEntidade);
    }

    @Override
    public ClienteEntidade buscarClientePorId(Long id) {
        var client = springClienteRepositorio.getClienteEntidadeById(id);

        if (client.isEmpty()) {
            throw new ClienteExcecao(ErroClienteEnum.CLIENTE_ID_NAO_ENCONTRADO);
        }

        return client.get();
    }

    @Override
    public ClienteEntidade buscarClientePorCPF(String cpf) {
        return springClienteRepositorio.getClienteEntidadeByCpf(cpf)
                .orElseThrow(() -> new ClienteExcecao(ErroClienteEnum.CLIENTE_CPF_NAO_ENCONTRADO));
    }

}
