package br.com.softcube.microservices.fornecedor.controllers;

import br.com.softcube.microservices.fornecedor.dtos.ItemPedidoDTO;
import br.com.softcube.microservices.fornecedor.models.Pedido;
import br.com.softcube.microservices.fornecedor.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido fazPedido(@RequestBody List<ItemPedidoDTO> produtos) {
        return pedidoService.realizaPedido(produtos);
    }

    @GetMapping(path = "/{id}")
    public Pedido pegaPedido(@PathVariable("id") Long pedidoId) {
        return pedidoService.pegaPedidoPor(pedidoId);
    }

}
