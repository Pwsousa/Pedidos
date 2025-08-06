package br.com.saboresconectados.pedidos.dto;


import br.com.saboresconectados.pedidos.model.Pedido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDto {

    private Long Id;
    private Integer quantidade;
    private String descicao;
    private Pedido pedido;
}
