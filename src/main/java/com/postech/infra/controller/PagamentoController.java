package com.postech.infra.controller;

import com.postech.application.usecases.PagamentoUseCases;
import com.postech.application.usecases.PedidoUseCases;
import com.postech.domain.entities.Pagamento;
import com.postech.domain.entities.Pedido;
import com.postech.domain.enums.EstadoPagamentoEnum;
import com.postech.infra.dto.request.PagamentoRequestDTO;
import com.postech.infra.mappers.PagamentoMapper;
import com.postech.infra.resource.PagamentoResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PagamentoController implements PagamentoResource {

    private final PagamentoUseCases useCases;

    private final PedidoUseCases pedidoUseCases;

    private final PagamentoMapper mapper;

    public PagamentoController(PagamentoUseCases useCases, PedidoUseCases pedidoUseCases, PagamentoMapper mapper) {
        this.useCases = useCases;
        this.pedidoUseCases = pedidoUseCases;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Object> criarPagamento(PagamentoRequestDTO pagamentoRequestDTO) {
        Pedido pedido = pedidoUseCases.consultaPorId(pagamentoRequestDTO.getIdPedido());

        Pagamento pagamento = useCases.criarPagamentoPix(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.paraResponseDTO(pagamento));
    }

    @Override
    public ResponseEntity<Object> getEstadoPagamento(PagamentoRequestDTO pagamentoRequestDTO) {

        EstadoPagamentoEnum estadoPagamento = useCases.getStatusPagamento(pagamentoRequestDTO.getIdPedido());

        return ResponseEntity.status(HttpStatus.CREATED).body(estadoPagamento);
    }
}
