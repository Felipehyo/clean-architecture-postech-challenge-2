package com.postech.infra.dto.request;

import com.postech.domain.enums.TipoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagamentoRequestDTO {

    private Long idPedido;

    private TipoPagamentoEnum tipoPagamento;
}
