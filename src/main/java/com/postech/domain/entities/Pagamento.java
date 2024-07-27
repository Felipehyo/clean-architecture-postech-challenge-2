package com.postech.domain.entities;

import com.postech.domain.enums.EstadoPagamentoEnum;
import com.postech.domain.enums.TipoMetodoPagamento;
import com.postech.domain.enums.TipoPagamentoEnum;
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

    private TipoPagamentoEnum tipoPagamento;

    private TipoMetodoPagamento metodoPagamento;

    private String qrCode;

    private Long pagamentoId;

    public Pagamento(Long id, Double valor, EstadoPagamentoEnum estadoPagamento, Pedido pedido, LocalDate dataPagamento, LocalDate dataCriacaoPagamento, TipoMetodoPagamento metodoPagamento, String qrCode, TipoPagamentoEnum tipoPagamento, Long pagamentoId) {
        this.id = id;
        this.valor = valor;
        this.estadoPagamento = estadoPagamento;
        this.pedido = pedido;
        this.dataPagamento = dataPagamento;
        this.dataCriacaoPagamento = dataCriacaoPagamento;
        this.metodoPagamento = metodoPagamento;
        this.qrCode = qrCode;
        this.tipoPagamento = tipoPagamento;
        this.pagamentoId = pagamentoId;
        validaEntidade();
    }

    public void validaEntidade() throws DominioException {
        ValidacaoUtils.validaArgumentoNaoNulo(getEstadoPagamento(), "O estado de pagamento não pode estar vazio!");
        ValidacaoUtils.validaArgumentoNaoNulo(getPedido(), "O pedido não pode estar vazio");
        ValidacaoUtils.validaArgumentoNaoNulo(getMetodoPagamento(), "É necessário que tenha um método de pagamento");
        ValidacaoUtils.validaArgumentoNaoNulo(getTipoPagamento(), "É necessário que tenha um tipo de pagamento definido");
        ValidacaoUtils.validaArgumentoNaoNulo(getDataCriacaoPagamento(), "É necessário que tenha uma data de criação de pagamento");
        ValidacaoUtils.validaValorPositivo(getValor(), "O valor do pagamento não pode ser negativo!");
        ValidacaoUtils.validaArgumentoNaoNulo(getValor(), "O valor do pagamento não pode ser nulo");
    }

    public Long getPagamentoId() {
        return pagamentoId;
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

    public TipoPagamentoEnum getTipoPagamento() {
        return tipoPagamento;
    }

    public TipoMetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setEstadoPagamento(EstadoPagamentoEnum estadoPagamento) {
        this.estadoPagamento = estadoPagamento;
    }
}
