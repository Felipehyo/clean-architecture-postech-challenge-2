package com.postech.domain.interfaces;

import com.postech.domain.entities.Pagamento;
import com.postech.domain.entities.Pedido;
import com.postech.domain.enums.EstadoPagamentoEnum;

public interface PagamentoServico {

    Pagamento criarPagamentoPix(Pedido pedido);

    EstadoPagamentoEnum getStatusPagamento(Long idProduto);
}
