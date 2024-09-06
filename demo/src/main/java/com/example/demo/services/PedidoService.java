package com.example.demo.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.PedidoDTO;
import com.example.demo.domain.Status;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.domain.exception.PedidoNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    private final KafkaTemplate<String,String> kafkaTemplate;

    public PedidoService(PedidoRepository pedidoRepository, KafkaTemplate<String,String> kafkaTemplate) {
        this.repository = pedidoRepository;
        this.kafkaTemplate = kafkaTemplate;
    }


    public Pedido insert(PedidoDTO pedidoDTO) {
        Pedido newPedido = new Pedido(pedidoDTO);
        this.repository.save(newPedido);
        kafkaTemplate.send("pedido-request", newPedido.getId());
        return newPedido;
    }

    public List<Pedido> getAll() {
        return this.repository.findAll();
    }

    public Pedido getById(String id) {
        return this.repository.findById(id)
                .orElseThrow(PedidoNotFoundException::new);
    }

    @KafkaListener(topics = "pedido-request", groupId = "teste-group")
    public void atualizarStatus(String id){
        Optional<Pedido> pedido = this.repository.findById(id);
        if(pedido.isPresent()){
            pedido.get().setStatus(Status.ENVIADO_TRANSPORTADORA);
            this.repository.save(pedido.get());
        }
    }

}
