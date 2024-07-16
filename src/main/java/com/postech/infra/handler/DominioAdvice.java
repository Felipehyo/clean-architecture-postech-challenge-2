package com.postech.infra.handler;

import com.postech.infra.dto.ErroDTO;
import com.postech.domain.exceptions.DominioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DominioAdvice {

    @ExceptionHandler(DominioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> dominioExcecaoHandler(DominioException excecao){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDTO("ERRO_DOMINIO", excecao.getMessage()));
    }
}
