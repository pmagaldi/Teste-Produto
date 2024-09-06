package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {
    @Id
    private String id;
    private String produto;
    private Status status;

    public Pedido(PedidoDTO pedidoDTO) {
        this.produto = pedidoDTO.produto();
        this.status = Status.AGUARDANDO_ENVIO;
    }
}
