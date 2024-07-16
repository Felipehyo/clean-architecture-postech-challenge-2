package com.postech.infra.controller;

import com.postech.application.usecases.PedidoUseCases;
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

    public PedidoController(PedidoUseCases useCases, PedidoMapper mapper) {
        this.useCases = useCases;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Object> criarPedido(PedidoRequestDTO pedidoDTO) {
        Pedido pedido = useCases.cadastrar(mapper.paraDominio(pedidoDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.paraResponseDto(pedido));
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
    public ResponseEntity<Object> consultarTodosPedidos() {
        List<Pedido> pedidos = useCases.consultaTodosOsPedidos();
        return ResponseEntity.ok().body(mapper.paraDTOLista(pedidos));
    }

    @Override
    public ResponseEntity<Object> atualizarEstadoPedidoPorId(Long id, EstadoPedidoEnum estado) {
        Pedido pedido = useCases.atualizaEstadoPorIdDoPedido(id, estado);
        return ResponseEntity.ok().body(mapper.paraResponseDto(pedido));
    }

    @Override
    public ResponseEntity<Object> checkout(Long id) { //todo implementar
        useCases.checkout(id);
        throw new PedidoException(ErroPedidoEnum.NAO_IMPLEMENTADO);
//        return ResponseEntity.ok().body(new FakeCheckoutDTO("Pedido realizado e enviado para a fila da cozinha"));
    }

    @Override
    public ResponseEntity<Object> deletarPedido(Long id) {
        useCases.deleta(id);
        return ResponseEntity.noContent().build();
    }

}