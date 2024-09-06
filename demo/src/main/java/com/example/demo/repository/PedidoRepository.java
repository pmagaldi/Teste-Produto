package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.Pedido;


@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {
}
