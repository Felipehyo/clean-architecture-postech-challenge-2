package com.postech.infra.mappers;

import com.postech.domain.entities.Pagamento;
import com.postech.infra.dto.response.PagamentoResponseDTO;
import com.postech.infra.persistence.entities.PagamentoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = PedidoMapper.class)
public interface PagamentoMapper {

    PagamentoEntity paraEntidade(Pagamento pagamento);
    Pagamento paraDominio(PagamentoEntity pagamentoEntity);
    PagamentoResponseDTO paraResponseDTO(Pagamento pagamento);
    List<PagamentoEntity> paraEntidadeLista(List<Pagamento> pagamentos);
    List<Pagamento> paraDominioLista(List<PagamentoEntity> pagamentoEntities);
}
