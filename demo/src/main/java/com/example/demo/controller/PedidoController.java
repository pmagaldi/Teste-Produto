package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.PedidoDTO;
import com.example.demo.services.PedidoService;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Pedido>> insertPedido(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoService.insert(pedidoDTO);
        return CompletableFuture.completedFuture(ResponseEntity.ok(pedido));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pedido>> getAll(){
        List<Pedido> pedidos = this.pedidoService.getAll();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable("id") String id) {
        Pedido pedido = pedidoService.getById(id);
        return ResponseEntity.ok(pedido);
    }
}
