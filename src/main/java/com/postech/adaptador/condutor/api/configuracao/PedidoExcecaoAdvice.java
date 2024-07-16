package com.postech.adaptador.condutor.api.configuracao;


import com.postech.adaptador.condutor.api.dto.ErroDTO;
import com.postech.nucleo.dominio.base.PedidoExcecao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PedidoExcecaoAdvice {

    @ExceptionHandler(value = {PedidoExcecao.class})
    ResponseEntity<Object> produtoExcecaoHandler(PedidoExcecao excecao) {
        return ResponseEntity.status(HttpStatus.valueOf(excecao.getErro().getHttpStatusCode())).body(new ErroDTO(excecao.getErro().name(), excecao.getErro().getDetalhe()));
    }

}
