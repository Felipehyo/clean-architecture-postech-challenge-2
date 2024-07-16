package com.postech.infra.resource;

import com.postech.domain.entities.Cliente;
import com.postech.infra.dto.request.ClienteRequestDTO;
import com.postech.infra.dto.ErroDTO;
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
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Recursos relacionados ao cliente")
public interface ClienteResource {

    @Operation(summary = "Cadastrar cliente", method = "POST", description = "Recurso para cadastrar cliente")
    @ApiResponses(value = {
            @ApiResponse(description = "Cliente cadastrado com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar cliente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> cadastrarCliente(@RequestBody ClienteRequestDTO cliente);

    @Operation(summary = "Buscar cliente", method = "GET", description = "Recurso para consultar cliente")
    @ApiResponses(value = {
            @ApiResponse(description = "Cliente encontrado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(description = "Erro ao consultar cliente", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(description = "Cliente não foi encontrado", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultarCliente(@RequestParam(value = "cpf") String cpf);

}