package com.postech.infra.resource;

import com.postech.domain.entities.Produto;
import com.postech.domain.enums.CategoriaProdutoEnum;
import com.postech.infra.dto.ErroDTO;
import com.postech.infra.dto.request.ProdutoRequestDTO;
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
@RequestMapping("/produtos")
@Tag(name = "Produto", description = "Recursos relacionados ao produto")
public interface ProdutoResource {

    @Operation(summary = "Criar produto", method = "POST", description = "Recurso para criar um produto")
    @ApiResponses(value = {
            @ApiResponse(description = "Produto criado com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "400", description = "Erro no cadastro do produto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> criarProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO);

    @Operation(summary = "Atualizar produto", method = "PUT", description = "Recurso para atualizar um produto")
    @ApiResponses(value = {
            @ApiResponse(description = "Produto atualizado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar produto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequestDTO);

    @Operation(summary = "Lista todos os produtos", method = "GET", description = "Recurso para listar todos os produtos. A consulta pode ser filtrada por categoria caso necessário.")
    @ApiResponses(value = {
            @ApiResponse(description = "Produtos encontrados com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "400", description = "Erro na consulta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produtos não foram encontrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> listarTodosProdutos(@RequestParam(value = "categoria", required = false) String categoria);

    @Operation(summary = "Consulta produto por ID", method = "GET", description = "Recurso para csonsultar produto por ID")
    @ApiResponses(value = {
            @ApiResponse(description = "Produto encontrado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "400", description = "Erro na consulta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> consultaProdutoPorID(@PathVariable Long id);

    @Operation(summary = "Consultar produto por categoria", method = "GET", description = "Recurso para consultar produto por categoria")
    @ApiResponses(value = {
            @ApiResponse(description = "Produtos pela categoria informada encontrados com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "400", description = "Erro na consulta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produtos não foram encontrados pela categoria informada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @GetMapping(value = "/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> consultaTodosPorCategoria(@RequestParam CategoriaProdutoEnum categoria);

    @Operation(summary = "Deleta produto por ID", method = "DELETE", description = "Recurso para deletar um produto por ID")
    @ApiResponses(value = {
            @ApiResponse(description = "Produto deletado com sucesso", responseCode = "204", content = @Content),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar produto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Object> deletarProdutoPorId(@PathVariable Long id);

}
