package com.postech.application.usecases;

import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.resources.payment.Payment;
import com.postech.domain.entities.Pedido;
import com.postech.domain.entities.PedidoProduto;
import com.postech.domain.enums.ErroPedidoEnum;
import com.postech.domain.exceptions.PedidoException;

import java.math.BigDecimal;
import java.util.List;

public class PagamentoUseCases {


    public String criarPagamentoPix(Pedido pedido) {
        try{

            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .transactionAmount(calcularValorPedido(pedido))
                    .description("Pagamento do pedido " + pedido.getId())
                    .paymentMethodId("pix")
                    .payer(PaymentPayerRequest.builder().email("alymaciel8@gmail.com").build())
                    .build();

            PaymentClient paymentClient = new PaymentClient();

            Payment payment = paymentClient.create(paymentCreateRequest);

            return payment.getPointOfInteraction().getTransactionData().getQrCode();
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
}
