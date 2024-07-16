package com.postech.domain.entities;

import com.postech.domain.exceptions.DominioException;
import com.postech.domain.utils.ValidacaoUtils;

import java.util.Optional;

public class Cliente {

    private Long id;

    private String nome;

    private String email;

    private CPF cpf;

    public Cliente(Long id, String nome, String email, CPF cpf) throws DominioException {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        validaEntidade();

    }

    public void validaEntidade() throws DominioException {
        ValidacaoUtils.validaArgumentoNaoVazio(nome, "O nome não pode estar vazio!");
        ValidacaoUtils.validaArgumentoNaoVazio(email, "O email não pode estar vazio!");
        if (!cpf.validar()) {
            throw new DominioException("O cpf informado é inválido");
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
