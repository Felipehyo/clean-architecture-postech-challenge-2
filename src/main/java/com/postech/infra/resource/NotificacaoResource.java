package com.postech.infra.resource;

import com.postech.infra.dto.ErroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noticacoes")
@Tag(name = "Notificacao", description = "Recursos relacionados a notificacao")
public interface NotificacaoResource {

    @Operation(summary = "Notificar atualização do estado do pagamento da API Mercado Pago", method = "POST", description = "Recurso para notificar estado do pagamento")
    @ApiResponses(value = {
            @ApiResponse(description = "Notificação do estado do pagamento feita com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao criar pagamento", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @PostMapping(value ="/mercadopago", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> notificaEstadoPagamentoMercadoPago(@RequestBody String notificacaoPagamento);
}
