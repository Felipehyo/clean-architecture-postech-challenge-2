package com.postech.domain.interfaces;

import com.postech.domain.entities.Pagamento;
import com.postech.domain.entities.Pedido;

public interface PagamentoInterface {

    Pagamento criarPagamento(Pedido pedido);

}
