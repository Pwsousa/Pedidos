package br.com.saboresconectados.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.saboresconectados.pedidos.model.Pedido;


public interface PedidosRepository extends JpaRepository<Pedido,Long>{

} 