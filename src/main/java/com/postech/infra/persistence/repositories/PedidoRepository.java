package com.postech.infra.persistence.repositories;

import com.postech.infra.persistence.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    Optional<PedidoEntity> getPedidoEntityById(@Param("id") Long id);

}
