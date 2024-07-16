package com.postech.adaptador.condutor.api.entidade;

import com.postech.nucleo.dominio.enums.CategoriaProdutoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class ProdutoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaProdutoEnum categoria;

    private Double preco;

}
