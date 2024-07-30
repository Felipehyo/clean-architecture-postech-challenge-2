package com.postech.infra.mercadopago.usecases;

import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.resources.payment.Payment;
import com.postech.application.usecases.PedidoUseCases;
import com.postech.domain.entities.Pagamento;
import com.postech.domain.entities.Pedido;
import com.postech.domain.enums.*;
import com.postech.domain.exceptions.PagamentoException;
import com.postech.domain.interfaces.PagamentoInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class MercadoPagoUseCase implements PagamentoInterface {

    private static final TipoPagamentoEnum TIPO_PAGAMENTO = TipoPagamentoEnum.MERCADO_PAGO;

    public MercadoPagoUseCase() {
    }

    @Override
    public Pagamento criarPagamento(Pedido pedido) {
        try{
            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .transactionAmount(pedido.getValorPedido(pedido))
                    .description("Pagamento do pedido " + pedido.getId())
                    .paymentMethodId("pix")
                    .payer(criaPagador(pedido))
                    .dateOfExpiration(OffsetDateTime.now().plusMinutes(15))
                    .notificationUrl(null)
                    .build();

            PaymentClient paymentClient = new PaymentClient();

            Payment payment = paymentClient.create(paymentCreateRequest);

            return new Pagamento(null, payment.getTransactionAmount().doubleValue(),
                    EstadoPagamentoEnum.PENDENTE_PAGAMENTO, pedido, null, payment.getDateCreated().toLocalDate(),
                    TipoMetodoPagamento.PIX, payment.getPointOfInteraction().getTransactionData().getQrCode(), TIPO_PAGAMENTO,
                    payment.getId());
        } catch (Exception e){
            throw new PagamentoException(ErroPagamentoEnum.ERRO_CRIAR_PAGAMENTO);
        }
    }


    private PaymentPayerRequest criaPagador(Pedido pedido) {
        return PaymentPayerRequest.builder()
                .email(pedido.getCliente().getEmail())
                .firstName(pedido.getCliente().getNome())
                .lastName("teste")
                .email(pedido.getCliente().getEmail())
                .identification(IdentificationRequest.builder().type("CPF").number(pedido.getCliente().getCpf().getNumero()).build())
                .build();
    }
}
