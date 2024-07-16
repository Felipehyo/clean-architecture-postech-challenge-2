package com.postech.adaptador.condutor.api.tratador;

import com.postech.adaptador.condutor.api.dto.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder(String mensagem, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ErroDTO("", mensagem), httpStatus);
    }
}
