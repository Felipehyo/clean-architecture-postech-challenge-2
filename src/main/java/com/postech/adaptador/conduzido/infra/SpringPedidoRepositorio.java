package com.postech.adaptador.conduzido.infra;

import com.postech.adaptador.condutor.api.entidade.PedidoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringPedidoRepositorio extends JpaRepository<PedidoEntidade, Long> {

    Optional<PedidoEntidade> getPedidoEntidadeById(@Param("id") Long id);

}
