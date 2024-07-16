package com.postech.infra.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.postech.infra.dto.request.ProdutoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoProdutoResponseDTO {

    private Long id;

    @JsonIgnore
    private PedidoResponseDTO pedido;

    private ProdutoRequestDTO produto;

    private int quantidade;

    @Override
    public String toString() {
        return "PedidoProdutoDTO{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}
