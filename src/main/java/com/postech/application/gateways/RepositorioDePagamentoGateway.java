package com.postech.application.gateways;

import com.postech.domain.entities.Pagamento;

public interface RepositorioDePagamentoGateway {

    Pagamento salvaPagamento(Pagamento pagamento);

    Pagamento consultaPagamentoPorId(Long id);

}
