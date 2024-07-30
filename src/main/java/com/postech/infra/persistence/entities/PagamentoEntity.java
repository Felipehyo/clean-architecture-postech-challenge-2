package com.postech.infra.persistence.entities;

import com.postech.domain.entities.Pedido;
import com.postech.domain.enums.EstadoPagamentoEnum;
import com.postech.domain.enums.TipoPagamentoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagamento")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    @Enumerated(EnumType.STRING)
    private EstadoPagamentoEnum estadoPagamento;

    @OneToOne
    private PedidoEntity pedido;

    private LocalDate dataPagamento;

    private LocalDate dataCriacaoPagamento;

    private String metodoPagamento;

    private String qrCode;

    private Long pagamentoId;

    @Enumerated(EnumType.STRING)
    private TipoPagamentoEnum tipoPagamento;


}

