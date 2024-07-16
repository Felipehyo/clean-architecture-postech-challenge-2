package com.postech.nucleo.aplicacao.servico;

import com.postech.adaptador.condutor.api.dto.PedidoDTO;
import com.postech.adaptador.condutor.api.dto.entrada.PedidoEntradaDTO;
import com.postech.adaptador.condutor.api.dto.entrada.PedidoProdutoEntradaDTO;
import com.postech.adaptador.condutor.api.entidade.PedidoEntidade;
import com.postech.adaptador.condutor.api.mapeador.MapeadorCliente;
import com.postech.adaptador.condutor.api.mapeador.MapeadorPedido;
import com.postech.adaptador.condutor.api.mapeador.MapeadorProduto;
import com.postech.nucleo.dominio.base.PedidoExcecao;
import com.postech.nucleo.dominio.enums.ErroPedidoEnum;
import com.postech.nucleo.dominio.enums.EstadoPedidoEnum;
import com.postech.nucleo.dominio.modelo.Pedido;
import com.postech.nucleo.dominio.modelo.PedidoProduto;
import com.postech.nucleo.dominio.repositorio.ClienteRepositorio;
import com.postech.nucleo.dominio.repositorio.PedidoRepositorio;
import com.postech.nucleo.dominio.repositorio.ProdutoRepositorio;

import java.util.ArrayList;
import java.util.List;

public class PedidoServicoImpl implements PedidoServico {

    private final PedidoRepositorio pedidoRepositorio;

    private final ProdutoRepositorio produtoRepository;

    private final ClienteRepositorio clienteRepositorio;

    public PedidoServicoImpl(PedidoRepositorio pedidoRepositorio, ProdutoRepositorio produtoRepository, ClienteRepositorio clienteRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.produtoRepository = produtoRepository;
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public PedidoDTO consultaPedidoPorId(Long id) {
        PedidoEntidade pedidoEntidade = pedidoRepositorio.consultaPedidoPorId(id);

        if (pedidoEntidade == null) {
            throw new PedidoExcecao(ErroPedidoEnum.PEDIDO_NAO_ENCONTRADO);
        }

        return MapeadorPedido.INSTANCIA.paraDto(MapeadorPedido.INSTANCIA.paraDominio(pedidoEntidade));
    }

    @Override
    public PedidoDTO criaPedido(PedidoEntradaDTO pedidoDTO) {

        var cliente = clienteRepositorio.buscarClientePorId(pedidoDTO.getClienteId());
        var listaProdutos = new ArrayList<PedidoProduto>();

        Pedido pedido = new Pedido(null, MapeadorCliente.INSTANCIA.paraDominio(cliente), EstadoPedidoEnum.PENDENTE_PAGAMENTO, null);

        Pedido pedidoSalvo = MapeadorPedido.INSTANCIA.paraDominio(pedidoRepositorio.salvaPedido(MapeadorPedido.INSTANCIA.paraEntidade(pedido)));

        for (PedidoProdutoEntradaDTO produto : pedidoDTO.getPedidosProdutos()) {
            var produtoEntidade = produtoRepository.consultaProdutoPorId(produto.getProdutoId());
            listaProdutos.add(new PedidoProduto(null, pedidoSalvo, MapeadorProduto.INSTANCIA.paraDominio(produtoEntidade), produto.getQuantidade()));
        }

        pedidoSalvo.setPedidosProdutos(listaProdutos);
        Pedido pedidoSalvoComProdutos = MapeadorPedido.INSTANCIA.paraDominio(pedidoRepositorio.salvaPedido(MapeadorPedido.INSTANCIA.paraEntidade(pedidoSalvo)));

        return MapeadorPedido.INSTANCIA.paraDto(pedidoSalvoComProdutos);
    }

    @Override
    public PedidoDTO atualizaEstadoPedidoPorId(Long id, EstadoPedidoEnum estado) {
        PedidoDTO pedidoDTO = this.consultaPedidoPorId(id);

        if (!isTransicaoEstadoValida(pedidoDTO.getEstado(), estado)) {
            throw new PedidoExcecao(ErroPedidoEnum.ESTADO_INVALIDO);
        }

        pedidoDTO.setEstado(estado);

        Pedido pedidoDominio = MapeadorPedido.INSTANCIA.paraDominio(pedidoDTO);

        pedidoRepositorio.salvaPedido(MapeadorPedido.INSTANCIA.paraEntidade(pedidoDominio));

        return pedidoDTO;
    }

    /**
     * Este método verifica se a transição de estado informada é permitida
     *
     * @param estadoAtual
     * @param novoEstado
     * @return
     */
    private boolean isTransicaoEstadoValida(EstadoPedidoEnum estadoAtual, EstadoPedidoEnum novoEstado) {
        return switch (estadoAtual) {
            case PENDENTE_PAGAMENTO -> novoEstado == EstadoPedidoEnum.PAGO || novoEstado == EstadoPedidoEnum.CANCELADO;
            case PAGO -> novoEstado == EstadoPedidoEnum.RECEBIDO || novoEstado == EstadoPedidoEnum.CANCELADO;
            case RECEBIDO -> novoEstado == EstadoPedidoEnum.PREPARANDO || novoEstado == EstadoPedidoEnum.CANCELADO;
            case PREPARANDO -> novoEstado == EstadoPedidoEnum.PRONTO || novoEstado == EstadoPedidoEnum.CANCELADO;
            case PRONTO -> novoEstado == EstadoPedidoEnum.FINALIZADO || novoEstado == EstadoPedidoEnum.CANCELADO;
            case FINALIZADO, CANCELADO ->
                    throw new PedidoExcecao(ErroPedidoEnum.ESTADO_INVALIDO); // Não pode mudar de FINALIZADO ou CANCELADO
        };
    }

    @Override
    public EstadoPedidoEnum notificaEstadoPedido(Long id) {
        PedidoDTO pedido = this.consultaPedidoPorId(id);
        return pedido.getEstado();
    }

    @Override
    public List<PedidoDTO> consultaTodosPedidos() {
        List<PedidoEntidade> pedidoEntidades = pedidoRepositorio.pegaTodosProdutos();

        if (pedidoEntidades.isEmpty()) {
            throw new PedidoExcecao(ErroPedidoEnum.PEDIDOS_NAO_ECONTRADOS);
        }

        List<Pedido> pedidos = MapeadorPedido.INSTANCIA.paraDominioListaEntidade(pedidoEntidades);


        return MapeadorPedido.INSTANCIA.paraDTOLista(pedidos);
    }

    @Override
    public void fakeCheckout(Long id) {
        this.atualizaEstadoPedidoPorId(id, EstadoPedidoEnum.PAGO);
        this.atualizaEstadoPedidoPorId(id, EstadoPedidoEnum.RECEBIDO);
    }

    @Override
    public void deletarPedido(Long id) {
        pedidoRepositorio.consultaPedidoPorId(id);
        pedidoRepositorio.deletaPedido(id);
    }

}
