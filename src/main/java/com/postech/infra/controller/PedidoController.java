package com.postech.infra.controller;

import com.postech.application.usecases.CriarPedidoUseCases;
import com.postech.application.usecases.PedidoUseCases;
import com.postech.application.usecases.SalvarPedidoUseCases;
import com.postech.domain.entities.Pedido;
import com.postech.domain.enums.ErroPedidoEnum;
import com.postech.domain.enums.EstadoPedidoEnum;
import com.postech.domain.exceptions.PedidoException;
import com.postech.infra.dto.request.PedidoRequestDTO;
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
    private final SalvarPedidoUseCases salvarPedidoUseCases;

    public PedidoController(PedidoUseCases useCases, PedidoMapper mapper, CriarPedidoUseCases criaPedidoUseCases, SalvarPedidoUseCases salvarPedidoUseCases) {
        this.useCases = useCases;
        this.mapper = mapper;
        this.criaPedidoUseCases = criaPedidoUseCases;
        this.salvarPedidoUseCases = salvarPedidoUseCases;
    }

    @Override
    public ResponseEntity<Object> criarPedido(PedidoRequestDTO pedidoDTO) {

        Pedido pedido = criaPedidoUseCases.criaPedido(pedidoDTO);

        Pedido pedidoSalvo = salvarPedidoUseCases.salvarPedido(pedido);

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
        return ResponseEntity.ok().body(pedido.getEstado());//todo
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
        return ResponseEntity.ok().body("Pedido realizado e enviado para a fila da cozinha");
    }

    @Override
    public ResponseEntity<Object> deletarPedido(Long id) {
        useCases.deleta(id);
        return ResponseEntity.noContent().build();
    }

}