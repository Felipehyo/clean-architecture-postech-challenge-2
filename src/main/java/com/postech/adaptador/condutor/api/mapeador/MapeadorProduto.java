package com.postech.adaptador.condutor.api.mapeador;

import com.postech.adaptador.condutor.api.entidade.ProdutoEntidade;
import com.postech.adaptador.condutor.api.dto.ProdutoDTO;
import com.postech.nucleo.dominio.modelo.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MapeadorProduto {

    MapeadorProduto INSTANCIA = Mappers.getMapper(MapeadorProduto.class);

    ProdutoEntidade paraEntidade(Produto produto);

    Produto paraDominio(ProdutoEntidade produtoEntidade);
    Produto paraDominio(ProdutoDTO produtoDTO);
    ProdutoDTO paraDTO(Produto produto);
    List<ProdutoEntidade> paraEntidadeLista(List<Produto> produtos);
    List<Produto> paraDominioLista(List<ProdutoEntidade> produtosEntidade);
    List<Produto> paraDominioListaDTO(List<ProdutoDTO> produtosDTO);
    List<ProdutoDTO> paraDTOListaDominio(List<Produto> produtos);


}
