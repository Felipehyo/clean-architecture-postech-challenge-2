package com.postech.infra.persistence.repositories;

import com.postech.domain.enums.CategoriaProdutoEnum;
import com.postech.infra.persistence.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Optional<ProdutoEntity> getProdutoEntityById(Long id);

    @Query(value = "SELECT p FROM ProdutoEntity p WHERE :categoria IS NULL OR p.categoria = :categoria")
    Optional<List<ProdutoEntity>> getProdutoEntity(@Param("categoria") CategoriaProdutoEnum categoria);

    Optional<List<ProdutoEntity>> getProdutoEntityByCategoria(CategoriaProdutoEnum categoria);

}
