package br.com.saboresconectados.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.saboresconectados.pedidos.model.ItemDoPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemDoPedido,Long>{
    
}
