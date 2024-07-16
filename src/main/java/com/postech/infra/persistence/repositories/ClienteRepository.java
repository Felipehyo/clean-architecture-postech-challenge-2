package com.postech.infra.persistence.repositories;

import com.postech.infra.persistence.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> getClienteEntityByCpf(String cpf);
    Optional<ClienteEntity> getClienteEntityById(Long id);
}
