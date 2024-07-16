package com.postech.adaptador.condutor.api.mapeador;

import com.postech.adaptador.condutor.api.dto.PedidoProdutoDTO;
import com.postech.adaptador.condutor.api.entidade.PedidoProdutoEntidade;
import com.postech.nucleo.dominio.modelo.PedidoProduto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface MapeadorPedidoProduto {

    MapeadorPedidoProduto INSTANCIA = Mappers.getMapper(MapeadorPedidoProduto.class);

    @Mapping(target = "pedido.cliente.cpf", source = "pedidoProduto.pedido.cliente.cpf.numero")
    @Mapping(target = "pedido.pedidosProdutos", ignore = true)
    PedidoProdutoEntidade paraEntidade(PedidoProduto pedidoProduto);


    @Mapping(target = "pedido.cliente.cpf.numero", source = "pedidoProdutoEntidade.pedido.cliente.cpf")
    @Mapping(target = "pedido.pedidosProdutos", ignore = true)
    PedidoProduto paraDominio(PedidoProdutoEntidade pedidoProdutoEntidade);

    @Mapping(target = "pedido.pedidosProdutos", ignore = true)
    PedidoProduto paraDominio(PedidoProdutoDTO pedidoProdutoDTO);

    @Mapping(target = "pedido.pedidosProdutos", ignore = true)
    PedidoProdutoDTO paraDTO(PedidoProduto pedidoProduto);

    List<PedidoProdutoEntidade> paraEntidadeLista(List<PedidoProduto> pedidosProdutos);

    List<PedidoProduto> paraDominioListaEntidade(List<PedidoProdutoEntidade> pedidosProdutosEntidade);

    List<PedidoProduto> paraDominioListaDTO(List<PedidoProdutoDTO> pedidosProdutosDTO);

    List<PedidoProdutoDTO> paraDTOLista(List<PedidoProduto> pedidosProdutos);

}
