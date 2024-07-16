package com.postech.adaptador.condutor.api.controlador;

import com.postech.adaptador.condutor.api.dto.ErroDTO;
import com.postech.adaptador.condutor.api.dto.ProdutoDTO;
import com.postech.nucleo.aplicacao.servico.ProdutoServico;
import com.postech.nucleo.dominio.enums.CategoriaProdutoEnum;
import com.postech.nucleo.dominio.modelo.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Produto", description = "Recursos relacionados ao produto")
@RestController
@RequestMapping("/produtos")
public class ControladorProduto {

    private final ProdutoServico produtoServico;

    @Autowired
    public ControladorProduto(ProdutoServico produtoServico) {
        this.produtoServico = produtoServico;
    }

    @Operation(summary = "Criar produto", method = "POST", description = "Recurso para criar um produto")
    @ApiResponses(value = {
            @ApiResponse(description = "Produto criado com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "400", description = "Erro no cadastro do produto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> criarProduto(@RequestBody ProdutoDTO produtoDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoServico.criaNovoProduto(produtoDTO));
    }

    @Operation(summary = "Atualizar produto", method = "PUT", description = "Recurso para atualizar um produto")
    @ApiResponses(value = {
            @ApiResponse(description = "Produto atualizado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar produto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok().body(produtoServico.atualizaProduto(id, produtoDTO));
    }

    @Operation(summary = "Lista todos os produtos", method = "GET", description = "Recurso para listar todos os produtos. A consulta pode ser filtrada por categoria caso necessário.")
    @ApiResponses(value = {
            @ApiResponse(description = "Produtos encontrados com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "400", description = "Erro na consulta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produtos não foram encontrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> listarTodosProdutos(@RequestParam(value = "categoria", required = false) String categoria) {
        return ResponseEntity.ok().body(produtoServico.consultaTodosProdutos(categoria));
    }

    @Operation(summary = "Consulta produto por ID", method = "GET", description = "Recurso para csonsultar produto por ID")
    @ApiResponses(value = {
            @ApiResponse(description = "Produto encontrado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "400", description = "Erro na consulta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> consultaProdutoPorID(@PathVariable Long id) {
        return ResponseEntity.ok().body(produtoServico.consultaProdutoPorId(id));
    }

    @Operation(summary = "Consultar produto por categoria", method = "GET", description = "Recurso para consultar produto por categoria")
    @ApiResponses(value = {
            @ApiResponse(description = "Produtos pela categoria informada encontrados com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "400", description = "Erro na consulta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produtos não foram encontrados pela categoria informada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @GetMapping(value = "/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> consultaTodosPorCategoria(@RequestParam CategoriaProdutoEnum categoria) {
        return ResponseEntity.ok().body(produtoServico.consultaProdutosPorCategoria(categoria));
    }

    @Operation(summary = "Deleta produto por ID", method = "DELETE", description = "Recurso para deletar um produto por ID")
    @ApiResponses(value = {
            @ApiResponse(description = "Produto deletado com sucesso", responseCode = "204", content = @Content),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar produto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Object> deletarProdutoPorId(@PathVariable Long id) {
        produtoServico.deletaProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }

}
