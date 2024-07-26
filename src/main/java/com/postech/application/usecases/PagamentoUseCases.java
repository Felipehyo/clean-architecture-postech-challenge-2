package com.postech.application.usecases;

import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.resources.payment.Payment;
import com.postech.application.gateways.RepositorioDePagamentoGateway;
import com.postech.domain.entities.Pagamento;
import com.postech.domain.entities.Pedido;
import com.postech.domain.enums.ErroPagamentoEnum;
import com.postech.domain.enums.ErroPedidoEnum;
import com.postech.domain.enums.EstadoPagamentoEnum;
import com.postech.domain.exceptions.PagamentoException;
import com.postech.domain.exceptions.PedidoException;
import com.postech.domain.interfaces.PagamentoInterface;


public class PagamentoUseCases {

    private PedidoUseCases pedidoUseCases;

    private RepositorioDePagamentoGateway repositorio;

    private PagamentoInterface pagamentoExternoUseCase;

    public PagamentoUseCases(PedidoUseCases pedidoUseCases, RepositorioDePagamentoGateway repositorio, PagamentoInterface pagamentoExternoUseCase) {
        this.pedidoUseCases = pedidoUseCases;
        this.repositorio = repositorio;
        this.pagamentoExternoUseCase = pagamentoExternoUseCase;
    }

    public Pagamento criarPagamentoPix(Pedido pedido) {
        try{
            Pagamento pagamento = pagamentoExternoUseCase.criarPagamento(pedido);
            return repositorio.salvaPagamento(pagamento);
        }catch (Exception e){
            throw new PedidoException(ErroPedidoEnum.ESTADO_INVALIDO);
        }
    }


    public EstadoPagamentoEnum getStatusPagamento(Long idProduto){
        Pagamento pagamento = repositorio.consultaPagamentoPorIdPedido(idProduto);

        if(pagamento == null){
            throw new PagamentoException(ErroPagamentoEnum.PAGAMENTO_NAO_ENCONTRADO_POR_ID_PEDIDO);
        }

        return pagamento.getEstadoPagamento();
    }
}
