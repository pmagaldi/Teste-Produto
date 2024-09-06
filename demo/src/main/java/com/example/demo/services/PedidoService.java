package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.PedidoDTO;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.domain.exception.PedidoNotFoundException;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.repository = pedidoRepository;
    }


    public Pedido insert(PedidoDTO pedidoDTO) {
        Pedido newPedido = new Pedido(pedidoDTO);
        this.repository.save(newPedido);

        return newPedido;
    }

    public List<Pedido> getAll() {
        return this.repository.findAll();
    }

    public Pedido getById(String id) {
        return this.repository.findById(id)
                .orElseThrow(PedidoNotFoundException::new);
    }

}
