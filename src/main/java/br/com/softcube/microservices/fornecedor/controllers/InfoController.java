package br.com.softcube.microservices.fornecedor.controllers;

import br.com.softcube.microservices.fornecedor.models.InfoFornecedor;
import br.com.softcube.microservices.fornecedor.services.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    private static final Logger LOG = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private InfoService infoService;

    @GetMapping(path = "/{estado}")
    public InfoFornecedor getInfoPorEstado(@PathVariable("estado") String estado) {
        LOG.info("Obtendo informações do fornecedor por estado para o estado de {}", estado);

        return infoService.getPorEstado(estado);
    }

}
