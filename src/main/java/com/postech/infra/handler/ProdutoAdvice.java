package com.postech.infra.handler;

import com.postech.infra.dto.ErroDTO;
import com.postech.domain.exceptions.ProdutoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProdutoAdvice {

    @ExceptionHandler(value = {ProdutoException.class})
    ResponseEntity<Object> produtoExcecaoHandler(ProdutoException excecao) {
        return ResponseEntity.status(HttpStatus.valueOf(excecao.getErro().getHttpStatusCode())).body(new ErroDTO(excecao.getErro().name(), excecao.getErro().getDetalhe()));
    }

}
