package com.postech.adaptador.condutor.api.mapeador;

import com.postech.adaptador.condutor.api.entidade.PedidoEntidade;
import com.postech.adaptador.condutor.api.dto.PedidoDTO;
import com.postech.nucleo.dominio.modelo.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = MapeadorPedidoProduto.class)
public interface MapeadorPedido {

    MapeadorPedido INSTANCIA = Mappers.getMapper(MapeadorPedido.class);

    @Mapping(target = "cliente.cpf", source = "pedido.cliente.cpf.numero")
    PedidoEntidade paraEntidade(Pedido pedido);

    @Mapping(target = "cliente.cpf.numero", source = "pedidoEntidade.cliente.cpf")
    Pedido paraDominio(PedidoEntidade pedidoEntidade);

    @Mapping(target = "cliente.cpf.numero", source = "pedido.cliente.cpf.numero")
    PedidoDTO paraDto(Pedido pedido);

    Pedido paraDominio(PedidoDTO pedidoDTO);

    List<PedidoEntidade> paraEntidadeLista(List<Pedido> pedidos);

    List<Pedido> paraDominioListaEntidade(List<PedidoEntidade> pedidosEntidade);

    List<Pedido> paraDominioListaDTO(List<PedidoDTO> pedidosDTO);

    List<PedidoDTO> paraDTOLista(List<Pedido> pedidos);

}
