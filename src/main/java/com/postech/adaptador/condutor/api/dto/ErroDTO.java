package com.postech.adaptador.condutor.api.dto;

import lombok.Data;

@Data
public class ErroDTO {

    private String codigo;
    private String detalhe;

    public ErroDTO(String estado, String detalhe) {
        this.codigo = estado;
        this.detalhe = detalhe;
    }

}
