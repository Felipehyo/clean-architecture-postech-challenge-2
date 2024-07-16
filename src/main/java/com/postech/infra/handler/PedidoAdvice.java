package com.postech.infra.handler;


import com.postech.infra.dto.ErroDTO;
import com.postech.domain.exceptions.PedidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PedidoAdvice {

    @ExceptionHandler(value = {PedidoException.class})
    ResponseEntity<Object> produtoExcecaoHandler(PedidoException excecao) {
        return ResponseEntity.status(HttpStatus.valueOf(excecao.getErro().getHttpStatusCode())).body(new ErroDTO(excecao.getErro().name(), excecao.getErro().getDetalhe()));
    }

}
