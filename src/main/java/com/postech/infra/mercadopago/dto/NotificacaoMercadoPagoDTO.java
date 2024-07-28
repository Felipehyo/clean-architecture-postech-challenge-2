package com.postech.infra.mercadopago.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoMercadoPagoDTO {

    @JsonProperty("action")
    private String acao;

    @JsonProperty("date_created")
    private LocalDate dataCriada;

    private Long pagamentoId;

    @JsonCreator
    public NotificacaoMercadoPagoDTO(@JsonProperty("action") String acao,
                  @JsonProperty("date_created") LocalDateTime dataCriada,
                  @JsonProperty("data") JsonNode data) {
        this.acao = acao;
        this.dataCriada = dataCriada.toLocalDate();
        this.pagamentoId = data != null ? data.get("id").asLong() : null;
    }

}
