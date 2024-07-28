package com.postech.application.usecases;

import com.postech.application.gateways.RepositorioDePagamentoGateway;
import com.postech.domain.entities.Pagamento;
import com.postech.domain.entities.Pedido;
import com.postech.domain.enums.ErroPagamentoEnum;
import com.postech.domain.enums.ErroPedidoEnum;
import com.postech.domain.enums.EstadoPagamentoEnum;
import com.postech.domain.exceptions.PagamentoException;
import com.postech.domain.exceptions.PedidoException;
import com.postech.domain.interfaces.PagamentoInterface;
import com.postech.infra.dto.request.NotificacaoPagamentoDTO;


public class PagamentoUseCases {

    private final RepositorioDePagamentoGateway repositorio;

    private final PagamentoInterface pagamentoExternoUseCase;

    public PagamentoUseCases(RepositorioDePagamentoGateway repositorio, PagamentoInterface pagamentoExternoUseCase) {
        this.repositorio = repositorio;
        this.pagamentoExternoUseCase = pagamentoExternoUseCase;
    }

    public Pagamento criarPagamentoPix(Pedido pedido) {
        try{
            Pagamento pagamento = pagamentoExternoUseCase.criarPagamento(pedido);
            return repositorio.salvaPagamento(pagamento);
        }catch (Exception e){
            throw new PedidoException(ErroPedidoEnum.ESTADO_INVALIDO);
        }
    }


    public EstadoPagamentoEnum getStatusPagamento(Long idProduto){
        Pagamento pagamento = repositorio.consultaPagamentoPorIdPedido(idProduto);

        if(pagamento == null){
            throw new PagamentoException(ErroPagamentoEnum.PAGAMENTO_NAO_ENCONTRADO_POR_ID_PEDIDO);
        }

        return pagamento.getEstadoPagamento();
    }

    public Pagamento getPagamentoPorIdPagamento(Long id){
        Pagamento pagamento = repositorio.consultaPagamentoPorIdPagamento(id);

        if(pagamento == null){
            throw new PagamentoException(ErroPagamentoEnum.PAGAMENTO_NAO_ENCONTRADO_POR_ID_PEDIDO);
        }

        return pagamento;
    }

    public void atualizaEstadoPagamento(NotificacaoPagamentoDTO notificacaoPagamentoDTO) {
        Pagamento pagamento = this.getPagamentoPorIdPagamento(notificacaoPagamentoDTO.getPagamentoId());

        pagamento.setEstadoPagamento(notificacaoPagamentoDTO.getEstadoPagamento());
        pagamento.setDataPagamento(notificacaoPagamentoDTO.getDataAttPagamento());

        repositorio.salvaPagamento(pagamento);
    }
}
