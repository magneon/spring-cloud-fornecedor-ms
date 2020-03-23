package br.com.softcube.microservices.fornecedor.services;

import br.com.softcube.microservices.fornecedor.dtos.ItemPedidoDTO;
import br.com.softcube.microservices.fornecedor.models.Pedido;
import br.com.softcube.microservices.fornecedor.models.PedidoItem;
import br.com.softcube.microservices.fornecedor.models.Produto;
import br.com.softcube.microservices.fornecedor.repositories.PedidoRepository;
import br.com.softcube.microservices.fornecedor.repositories.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private static final Logger LOG = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido realizaPedido(List<ItemPedidoDTO> itensPedido) {
        LOG.info("Convertendo itens do pedido para objeto do modelo");
        List<PedidoItem> itens = convertToPedidoItem(itensPedido);
        LOG.info("Convertido {}", itens);

        LOG.info("Criando o pedido");
        Pedido pedido = new Pedido(itens);
        pedido.setTempoPreparo(itens.size());
        LOG.info("Pedido criado {}", pedido);

        return pedidoRepository.save(pedido);
    }

    private List<PedidoItem> convertToPedidoItem(List<ItemPedidoDTO> itensPedido) {
        List<Long> ids = itensPedido.stream().map(ItemPedidoDTO::getId).collect(Collectors.toList());

        List<Produto> produtos = produtoRepository.findByIdIn(ids);

        List<PedidoItem> itens = itensPedido.stream().map(itemPedido -> {
            Produto produto = produtos.stream().filter(p -> p.getId().equals(itemPedido.getId())).findFirst().get();

            PedidoItem pedidoItem = new PedidoItem();
            pedidoItem.setProduto(produto);
            pedidoItem.setQuantidade(itemPedido.getQuantidade());

            return pedidoItem;
        }).collect(Collectors.toList());

        return itens;
    }

    public Pedido pegaPedidoPor(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            return pedido.get();
        } else {
            return null;
        }
    }
}
