package com.postech.infra.mappers;

import com.postech.domain.entities.PedidoProduto;
import com.postech.infra.dto.response.PedidoProdutoResponseDTO;
import com.postech.infra.persistence.entities.PedidoProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoProdutoMapper {

    @Mapping(target = "pedido.cliente.cpf", source = "pedidoProduto.pedido.cliente.cpf.numero")
    @Mapping(target = "pedido.pedidosProdutos", ignore = true)
    PedidoProdutoEntity paraEntidade(PedidoProduto pedidoProduto);

    @Mapping(target = "pedido.cliente.cpf.numero", source = "pedidoProdutoEntity.pedido.cliente.cpf")
    @Mapping(target = "pedido.pedidosProdutos", ignore = true)
    PedidoProduto paraDominio(PedidoProdutoEntity pedidoProdutoEntity);

    @Mapping(target = "pedido.pedidosProdutos", ignore = true)
    PedidoProduto paraDominio(PedidoProdutoResponseDTO pedidoProdutoResponseDTO);

    @Mapping(target = "pedido.pedidosProdutos", ignore = true)
    PedidoProdutoResponseDTO paraDTO(PedidoProduto pedidoProduto);

    List<PedidoProdutoEntity> paraEntidadeLista(List<PedidoProduto> pedidosProdutos);

    List<PedidoProduto> paraDominioListaEntidade(List<PedidoProdutoEntity> pedidosProdutosEntidade);

    List<PedidoProduto> paraDominioListaDTO(List<PedidoProdutoResponseDTO> pedidosProdutosDTO);

    List<PedidoProdutoResponseDTO> paraDTOLista(List<PedidoProduto> pedidosProdutos);

}
