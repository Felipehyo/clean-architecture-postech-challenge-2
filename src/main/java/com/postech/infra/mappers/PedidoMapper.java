package com.postech.infra.mappers;

import com.postech.domain.entities.Pedido;
import com.postech.infra.dto.request.PedidoRequestDTO;
import com.postech.infra.dto.response.PedidoResponseDTO;
import com.postech.infra.persistence.entities.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = PedidoProdutoMapper.class)
public interface PedidoMapper {

    @Mapping(target = "cliente.cpf", source = "pedido.cliente.cpf.numero")
    PedidoEntity paraEntidade(Pedido pedido);

    @Mapping(target = "cliente.cpf.numero", source = "pedidoEntity.cliente.cpf")
    Pedido paraDominio(PedidoEntity pedidoEntity);

    @Mapping(target = "cliente.cpf.numero", source = "pedido.cliente.cpf.numero")
    PedidoResponseDTO paraResponseDto(Pedido pedido);
    Pedido paraDominio(PedidoResponseDTO pedidoResponseDTO);
    Pedido paraDominio(PedidoRequestDTO pedidoRequestDTO);

    List<PedidoEntity> paraEntidadeLista(List<Pedido> pedidos);

    List<Pedido> paraDominioListaEntidade(List<PedidoEntity> pedidosEntidade);

    List<Pedido> paraDominioListaDTO(List<PedidoResponseDTO> pedidosDTO);

    List<PedidoResponseDTO> paraDTOLista(List<Pedido> pedidos);

}
