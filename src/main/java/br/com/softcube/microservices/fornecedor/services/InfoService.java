package br.com.softcube.microservices.fornecedor.services;

import br.com.softcube.microservices.fornecedor.models.InfoFornecedor;
import br.com.softcube.microservices.fornecedor.repositories.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    public InfoFornecedor getPorEstado(String state) {
        return infoRepository.findByEstado(state);
    }
}
