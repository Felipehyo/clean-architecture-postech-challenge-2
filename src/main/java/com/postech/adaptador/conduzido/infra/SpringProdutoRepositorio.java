package com.postech.adaptador.conduzido.infra;

import com.postech.adaptador.condutor.api.entidade.ProdutoEntidade;
import com.postech.nucleo.dominio.enums.CategoriaProdutoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringProdutoRepositorio extends JpaRepository<ProdutoEntidade, Long> {

    Optional<ProdutoEntidade> getProdutoEntidadeById(Long id);

    @Query(value = "SELECT p FROM ProdutoEntidade p WHERE :categoria IS NULL OR p.categoria = :categoria")
    Optional<List<ProdutoEntidade>> getProdutoEntidade(@Param("categoria") CategoriaProdutoEnum categoria);
    Optional<List<ProdutoEntidade>> getProdutoEntidadeByCategoria(CategoriaProdutoEnum categoria);

}
