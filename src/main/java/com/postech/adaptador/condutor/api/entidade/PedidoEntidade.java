package com.postech.adaptador.condutor.api.entidade;

import com.postech.nucleo.dominio.enums.EstadoPedidoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class PedidoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ClienteEntidade cliente;

    @Enumerated(EnumType.STRING)
    private EstadoPedidoEnum estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoProdutoEntidade> pedidosProdutos = new ArrayList<>();
}
