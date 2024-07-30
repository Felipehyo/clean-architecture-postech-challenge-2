package com.postech.infra.mercadopago.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.postech.domain.enums.EstadoPagamentoEnum;
import com.postech.domain.interfaces.NotificacaoExternoInterface;
import com.postech.application.utils.NotificacaoPagamento;
import com.postech.infra.mercadopago.dto.NotificacaoMercadoPagoDTO;

public class NotificacaoMercadoPagoUseCase implements NotificacaoExternoInterface {

    @Override
    public NotificacaoPagamento geraNotificaoPagamento(String jsonNotificacao) {

        ObjectMapper objectMapper  = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try{
            NotificacaoMercadoPagoDTO notificacaoMercadoPagoDTO = objectMapper.readValue(jsonNotificacao, NotificacaoMercadoPagoDTO.class);

            EstadoPagamentoEnum estadoPagamentoEnum = EstadoPagamentoEnum.PENDENTE_PAGAMENTO;

            if(notificacaoMercadoPagoDTO.getAcao().equals("payment.updated")){
                estadoPagamentoEnum = EstadoPagamentoEnum.PAGO;
            }

            return new NotificacaoPagamento(estadoPagamentoEnum, notificacaoMercadoPagoDTO.getDataCriada(), notificacaoMercadoPagoDTO.getPagamentoId());
        } catch (JsonProcessingException e) {
            return new NotificacaoPagamento(EstadoPagamentoEnum.PENDENTE_PAGAMENTO,  null, null);
        }
    }
}
