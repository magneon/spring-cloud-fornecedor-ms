package br.com.softcube.microservices.fornecedor.services;

import br.com.softcube.microservices.fornecedor.models.Produto;
import br.com.softcube.microservices.fornecedor.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getProdutosPorEstado(String estado) {
        return produtoRepository.findByEstado(estado);
    }
}
