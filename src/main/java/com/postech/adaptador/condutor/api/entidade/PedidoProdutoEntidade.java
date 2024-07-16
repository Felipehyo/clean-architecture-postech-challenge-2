package com.postech.adaptador.condutor.api.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido_produto")
public class PedidoProdutoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PedidoEntidade pedido;

    @ManyToOne
    private ProdutoEntidade produto;

    private int quantidade;

    @Override
    public String toString() {
        return "PedidoProdutoEntidade{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}
