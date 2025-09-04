package br.com.saboresconectados.pedidos.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDto {

    private Long Id;
    private Integer quantidade;
    private String descicao;

}
