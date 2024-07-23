package com.postech.application.usecases;

import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.resources.payment.Payment;
import com.postech.application.gateways.RepositorioDePagamentoGateway;
import com.postech.domain.entities.Pagamento;
import com.postech.domain.entities.Pedido;
import com.postech.domain.entities.PedidoProduto;
import com.postech.domain.enums.ErroPedidoEnum;
import com.postech.domain.enums.EstadoPagamentoEnum;
import com.postech.domain.exceptions.PedidoException;

import java.math.BigDecimal;
import java.util.List;

public class PagamentoUseCases {

    private PedidoUseCases pedidoUseCases;

    private RepositorioDePagamentoGateway repositorio;

    public PagamentoUseCases(PedidoUseCases pedidoUseCases, RepositorioDePagamentoGateway repositorio) {
        this.pedidoUseCases = pedidoUseCases;
        this.repositorio = repositorio;
    }

    public Pagamento criarPagamentoPix(Pedido pedido) {
        try{

            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .transactionAmount(calcularValorPedido(pedido))
                    .description("Pagamento do pedido " + pedido.getId())
                    .paymentMethodId("pix")
                    .payer(PaymentPayerRequest.builder().email("alymaciel8@gmail.com").build())
                    .build();

            PaymentClient paymentClient = new PaymentClient();

            Payment payment = paymentClient.create(paymentCreateRequest);

            return repositorio.salvaPagamento(new Pagamento(null, payment.getTransactionAmount().doubleValue(),
                    EstadoPagamentoEnum.PENDENTE_PAGAMENTO, pedido, null, payment.getDateCreated().toLocalDate(),
                    "pix", payment.getPointOfInteraction().getTransactionData().getQrCode()));
        }catch (Exception e){
            throw new PedidoException(ErroPedidoEnum.ESTADO_INVALIDO);
        }
    }

    private BigDecimal calcularValorPedido(Pedido pedido) {
        List<PedidoProduto> pedidosProdutos = pedido.getPedidosProdutos();

        double sum = pedidosProdutos.stream().mapToDouble(x -> x.getProduto().getPreco() * x.getQuantidade()).sum();

        return BigDecimal.valueOf(sum);
    }

    private PaymentPayerRequest criaPayer(Pedido pedido) {
        return PaymentPayerRequest.builder().id(pedido.getId().toString())
                .email(pedido.getCliente().getEmail())
                .firstName(pedido.getCliente().getNome())
                .lastName("teste")
                .email(pedido.getCliente().getEmail())
                .identification(IdentificationRequest.builder().type("CPF").number(pedido.getCliente().getCpf().getNumero()).build())
                .build();
    }

    public EstadoPagamentoEnum getStatusPagamento(Long idProduto){
        Pagamento pagamento = repositorio.consultaPagamentoPorIdPedido(idProduto);
        return pagamento.getEstadoPagamento();
    }
}
