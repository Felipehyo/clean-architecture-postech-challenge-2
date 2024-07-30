package com.postech.infra.handler;

import com.postech.domain.exceptions.PagamentoException;
import com.postech.infra.dto.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PagamentoAdvice {

    @ExceptionHandler(value = {PagamentoException.class})
    ResponseEntity<Object> pagamentoExcecaoHandler(PagamentoException excecao) {
        return ResponseEntity.status(HttpStatus.valueOf(excecao.getErro().getHttpStatusCode())).body(new ErroDTO(excecao.getErro().name(), excecao.getErro().getDetalhe()));
    }
}
