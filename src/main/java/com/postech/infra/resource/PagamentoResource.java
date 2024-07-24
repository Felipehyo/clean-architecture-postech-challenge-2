package com.postech.infra.resource;

import com.postech.infra.dto.ErroDTO;
import com.postech.infra.dto.request.PagamentoRequestDTO;
import com.postech.infra.dto.request.PedidoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamento", description = "Recursos relacionados ao pagamento")
public interface PagamentoResource {

    @Operation(summary = "Criar pagamento", method = "POST", description = "Recurso para criar um pagamento")
    @ApiResponses(value = {
            @ApiResponse(description = "Pagamento criado com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagamentoRequestDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao criar pagamento", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> criarPagamento(@RequestBody PagamentoRequestDTO pagamentoRequestDTO);


    @Operation(summary = "Pegar estado pagamento", method = "GET", description = "Recurso para criar um pagamento")
    @ApiResponses(value = {
            @ApiResponse(description = "Estado do pagamento encontrado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagamentoRequestDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao procurar estado do pagamento", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getEstadoPagamento(PagamentoRequestDTO pagamentoRequestDTO);


}
