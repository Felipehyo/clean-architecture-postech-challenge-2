package com.postech.infra.controller;

import com.postech.application.usecases.CriarPedidoUseCases;
import com.postech.application.usecases.PedidoUseCases;
import com.postech.application.usecases.AtualizarPedidoUseCases;
import com.postech.domain.entities.Pedido;
import com.postech.domain.enums.EstadoPedidoEnum;
import com.postech.infra.dto.request.PedidoRequestDTO;
import com.postech.infra.dto.response.CheckoutResponseDTO;
import com.postech.infra.mappers.PedidoMapper;
import com.postech.infra.resource.PedidoResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoController implements PedidoResource {

    private final PedidoUseCases useCases;
    private final PedidoMapper mapper;
    private final CriarPedidoUseCases criaPedidoUseCases;
    private final AtualizarPedidoUseCases atualizarPedidoUseCases;

    public PedidoController(PedidoUseCases useCases, PedidoMapper mapper, CriarPedidoUseCases criaPedidoUseCases, AtualizarPedidoUseCases atualizarPedidoUseCases) {
        this.useCases = useCases;
        this.mapper = mapper;
        this.criaPedidoUseCases = criaPedidoUseCases;
        this.atualizarPedidoUseCases = atualizarPedidoUseCases;
    }

    @Override
    public ResponseEntity<Object> criarPedido(PedidoRequestDTO pedidoDTO) {

        Pedido pedido = criaPedidoUseCases.criaPedido(pedidoDTO);

        Pedido pedidoSalvo = atualizarPedidoUseCases.salvarPedido(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.paraResponseDto(pedidoSalvo));
    }

    @Override
    public ResponseEntity<Object> consultarPedidoPorId(Long id) {
        Pedido pedido = useCases.consultaPorId(id);
        return ResponseEntity.ok().body(mapper.paraResponseDto(pedido));
    }

    @Override
    public ResponseEntity<Object> notificarPedidoEstado(Long id) {
        Pedido pedido = useCases.notificaEstado(id);
        return ResponseEntity.ok().body(pedido.getEstado());
    }


    @Override
    public ResponseEntity<Object> listarPedidos() {
        List<Pedido> pedidos = useCases.listarPedidos();
        return ResponseEntity.ok().body(mapper.paraDTOLista(pedidos));
    }

    @Override
    public ResponseEntity<Object> atualizarEstadoPedidoPorId(Long id, EstadoPedidoEnum estado) {
        Pedido pedido = useCases.atualizaEstadoPorIdDoPedido(id, estado);
        return ResponseEntity.ok().body(mapper.paraResponseDto(pedido));
    }

    @Override
    public ResponseEntity<Object> checkout(Long id) {
        Pedido checkout = useCases.checkout(id);
        return ResponseEntity.ok().body(new CheckoutResponseDTO("Pedido realizado e enviado para a fila da cozinha", mapper.paraResponseDto(checkout)));
    }

    @Override
    public ResponseEntity<Object> deletarPedido(Long id) {
        useCases.deleta(id);
        return ResponseEntity.noContent().build();
    }

}