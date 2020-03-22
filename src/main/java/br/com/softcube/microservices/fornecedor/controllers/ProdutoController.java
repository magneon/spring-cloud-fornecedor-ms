package br.com.softcube.microservices.fornecedor.controllers;

import br.com.softcube.microservices.fornecedor.models.Produto;
import br.com.softcube.microservices.fornecedor.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(path = "/{estado}")
    public ResponseEntity<List<Produto>> pegaProdutosPorEstado(@PathVariable("estado") String estado) {
        List<Produto> produtos = produtoService.getProdutosPorEstado(estado);
        if (produtos == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(produtos);
        }
    }

}
