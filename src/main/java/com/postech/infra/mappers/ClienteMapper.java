package com.postech.infra.mappers;

import com.postech.domain.entities.Cliente;
import com.postech.infra.dto.request.ClienteRequestDTO;
import com.postech.infra.dto.response.ClienteResponseDTO;
import com.postech.infra.persistence.entities.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "cpf", source = "cliente.cpf.numero")
    ClienteEntity paraEntidade(Cliente cliente);

    @Mapping(target = "cpf.numero", source = "clienteEntity.cpf")
    Cliente paraDominio(ClienteEntity clienteEntity);

    Cliente paraDominio(ClienteRequestDTO clienteRequestDTO);

    @Mapping(target = "cpf", source = "cliente.cpf.numero")
    ClienteResponseDTO paraDTO(Cliente cliente);
}
