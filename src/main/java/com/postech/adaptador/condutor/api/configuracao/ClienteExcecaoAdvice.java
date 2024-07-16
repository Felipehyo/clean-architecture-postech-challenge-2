package com.postech.adaptador.condutor.api.configuracao;

import com.postech.adaptador.condutor.api.dto.ErroDTO;
import com.postech.nucleo.dominio.base.ClienteExcecao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClienteExcecaoAdvice {

    @ExceptionHandler(value = {ClienteExcecao.class})
    ResponseEntity<Object> clienteExcecaoHandler(ClienteExcecao excecao) {
        return ResponseEntity.status(HttpStatus.valueOf(excecao.getErro().getHttpStatusCode())).body(new ErroDTO(excecao.getErro().name(), excecao.getErro().getDetalhe()));
    }

}
