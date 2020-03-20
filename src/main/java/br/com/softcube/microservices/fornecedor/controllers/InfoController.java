package br.com.softcube.microservices.fornecedor.controllers;

import br.com.softcube.microservices.fornecedor.models.InfoFornecedor;
import br.com.softcube.microservices.fornecedor.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping(path = "/{estado}")
    public InfoFornecedor getInfoPorEstado(@PathVariable("estado") String estado) {
        return infoService.getPorEstado(estado);
    }

}
