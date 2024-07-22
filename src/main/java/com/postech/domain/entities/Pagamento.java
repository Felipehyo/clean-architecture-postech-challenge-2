package com.postech.domain.entities;

import com.postech.domain.enums.EstadoPagamentoEnum;
import com.postech.domain.exceptions.DominioException;
import com.postech.domain.utils.ValidacaoUtils;

import java.time.LocalDate;

public class Pagamento {

    private Long id;

    private Double valor;

    private EstadoPagamentoEnum estadoPagamento;

    private Pedido pedido;

    private LocalDate dataPagamento;

    private LocalDate dataCriacaoPagamento;

    private String metodoPagamento;

    private String qrCode;

    public Pagamento(Long id, Double valor, EstadoPagamentoEnum estadoPagamento, Pedido pedido, LocalDate dataPagamento, LocalDate dataCriacaoPagamento, String metodoPagamento, String qrCode) {
        this.id = id;
        this.valor = valor;
        this.estadoPagamento = estadoPagamento;
        this.pedido = pedido;
        this.dataPagamento = dataPagamento;
        this.dataCriacaoPagamento = dataCriacaoPagamento;
        this.metodoPagamento = metodoPagamento;
        this.qrCode = qrCode;
        validaEntidade();
    }

    public void validaEntidade() throws DominioException {
        ValidacaoUtils.validaArgumentoNaoNulo(getEstadoPagamento(), "O estado do pedido não pode estar vazio!");
    }

    public String getQrCode() {
        return qrCode;
    }

    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public EstadoPagamentoEnum getEstadoPagamento() {
        return estadoPagamento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public LocalDate getDataCriacaoPagamento() {
        return dataCriacaoPagamento;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }
}
