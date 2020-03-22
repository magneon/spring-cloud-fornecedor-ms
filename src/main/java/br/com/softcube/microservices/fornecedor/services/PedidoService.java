package br.com.softcube.microservices.fornecedor.services;

import br.com.softcube.microservices.fornecedor.dtos.ItemPedidoDTO;
import br.com.softcube.microservices.fornecedor.models.Pedido;
import br.com.softcube.microservices.fornecedor.models.PedidoItem;
import br.com.softcube.microservices.fornecedor.models.Produto;
import br.com.softcube.microservices.fornecedor.repositories.PedidoRepository;
import br.com.softcube.microservices.fornecedor.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido realizaPedido(List<ItemPedidoDTO> itensPedido) {
        List<PedidoItem> itens = convertToPedidoItem(itensPedido);

        Pedido pedido = new Pedido(itens);
        pedido.setTempoPreparo(itens.size());

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
