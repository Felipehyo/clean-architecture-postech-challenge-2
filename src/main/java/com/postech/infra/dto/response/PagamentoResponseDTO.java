package com.postech.infra.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.postech.domain.entities.Pedido;
import com.postech.domain.enums.EstadoPagamentoEnum;
import com.postech.domain.enums.TipoMetodoPagamento;
import com.postech.domain.enums.TipoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagamentoResponseDTO {

    private Long id;

    private Double valor;

    private EstadoPagamentoEnum estadoPagamento;

    @JsonIgnore
    private PedidoResponseDTO pedido;

    private LocalDate dataPagamento;

    private LocalDate dataCriacaoPagamento;

    private TipoPagamentoEnum tipoPagamento;

    private TipoMetodoPagamento metodoPagamento;

    private String qrCode;

    private Long pagamentoId;

}
