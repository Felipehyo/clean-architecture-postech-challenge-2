package com.postech.infra.persistence.repositories;

import com.postech.infra.persistence.entities.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {


    Optional<PagamentoEntity> getPagamentoEntityByPedidoId(Long id);
    Optional<PagamentoEntity> getPagamentoEntityByPagamentoId(Long id);
}
