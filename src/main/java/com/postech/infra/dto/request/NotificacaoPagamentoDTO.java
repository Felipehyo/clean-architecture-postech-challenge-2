package com.postech.infra.dto.request;

import com.postech.domain.enums.EstadoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificacaoPagamentoDTO {

    private EstadoPagamentoEnum estadoPagamento;

    private LocalDate dataAttPagamento;

    private Long pagamentoId;
}
