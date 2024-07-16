package com.postech.nucleo.dominio.modelo;

import com.postech.nucleo.dominio.base.AgregacaoInterface;
import com.postech.nucleo.dominio.base.DominioExcecao;
import com.postech.nucleo.dominio.base.PreocupacaoAssercao;

import java.util.Optional;

public class Cliente implements AgregacaoInterface {

    private Long id;

    private String nome;

    private String email;

    private CPF cpf;

    public Cliente(Long id, String nome, String email, CPF cpf) throws DominioExcecao {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        validaEntidade();

    }

    public void validaEntidade() throws DominioExcecao {
        PreocupacaoAssercao.validaArgumentoNaoVazio(nome, "O nome não pode estar vazio!");
        PreocupacaoAssercao.validaArgumentoNaoVazio(email, "O email não pode estar vazio!");
        if(!cpf.validar()){
            throw new DominioExcecao("O cpf informado é inválido");
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }


    public CPF getCpf() {
        return cpf;
    }

    public String getNumeroCpf() {
        return Optional.ofNullable(cpf)
                .map(CPF::getNumero)
                .orElse(null);
    }

}
