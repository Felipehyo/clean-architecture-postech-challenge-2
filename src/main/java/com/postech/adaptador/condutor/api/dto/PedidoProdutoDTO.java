package com.postech.adaptador.condutor.api.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoProdutoDTO {

    private Long id;

    @JsonIgnore
    private PedidoDTO pedido;

    private ProdutoDTO produto;

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
