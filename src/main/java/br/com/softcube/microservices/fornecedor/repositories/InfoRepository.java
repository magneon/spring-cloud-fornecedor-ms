package br.com.softcube.microservices.fornecedor.repositories;

import br.com.softcube.microservices.fornecedor.models.InfoFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<InfoFornecedor, Long> {

    InfoFornecedor findByEstado(String estado);

}