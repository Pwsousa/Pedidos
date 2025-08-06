package br.com.saboresconectados.pedidos.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.saboresconectados.pedidos.model.ItemDoPedido;


@Getter
@Setter
public class PedidosDto {
    
    private Long Id;
    private LocalDateTime dataPedido;
    private Status status;
    private List<ItemDoPedido> itens = new ArrayList<>();
}
