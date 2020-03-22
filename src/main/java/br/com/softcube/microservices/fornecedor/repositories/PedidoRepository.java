package br.com.softcube.microservices.fornecedor.repositories;

import br.com.softcube.microservices.fornecedor.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
