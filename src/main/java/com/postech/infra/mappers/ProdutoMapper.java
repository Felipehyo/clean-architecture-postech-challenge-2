package com.postech.infra.mappers;

import com.postech.domain.entities.Produto;
import com.postech.infra.dto.request.ProdutoRequestDTO;
import com.postech.infra.persistence.entities.ProdutoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoEntity paraEntidade(Produto produto);
    Produto paraDominio(ProdutoEntity produtoEntity);
    Produto paraDominio(ProdutoRequestDTO produtoRequestDTO);
    Produto paraDominio(Produto produto);
    ProdutoRequestDTO paraDTO(Produto produto);
    List<ProdutoEntity> paraEntidadeLista(List<Produto> produtos);
    List<Produto> paraDominioLista(List<ProdutoEntity> produtosEntidade);
    List<Produto> paraDominioListaDTO(List<ProdutoRequestDTO> produtosDTO);
    List<ProdutoRequestDTO> paraDTOListaDominio(List<Produto> produtos);


}
