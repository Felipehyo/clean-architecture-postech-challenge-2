package com.postech.application.utils;

import com.postech.domain.enums.EstadoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificacaoPagamento {

    private EstadoPagamentoEnum estadoPagamento;

    private LocalDate dataAttPagamento;

    private String pagamentoId;
}
