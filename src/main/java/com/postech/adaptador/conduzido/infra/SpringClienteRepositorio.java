package com.postech.adaptador.conduzido.infra;

import com.postech.adaptador.condutor.api.entidade.ClienteEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringClienteRepositorio extends JpaRepository<ClienteEntidade, Long> {

    Optional<ClienteEntidade> getClienteEntidadeByCpf(String cpf);
    Optional<ClienteEntidade> getClienteEntidadeById(Long id);
}
