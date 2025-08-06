package br.com.saboresconectados.pedidos.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.saboresconectados.pedidos.dto.PedidosDto;
import br.com.saboresconectados.pedidos.model.Pedido;
import br.com.saboresconectados.pedidos.model.Status;
import br.com.saboresconectados.pedidos.repository.PedidosRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {
    
    @Autowired
    private PedidosRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PedidosDto> obterTodos(Pageable paginacao){
        return repository
        .findAll(paginacao)
        .map(p -> modelMapper.map(p, PedidosDto.class));
    }

    public PedidosDto obterPorId(Long id){
        Pedido pedidos = repository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(pedidos, PedidosDto.class);
    }

    public PedidosDto criarPedido(PedidosDto dto){
        Pedido pedidos = modelMapper.map(dto, Pedido.class);
        pedidos.setStatus(Status.EM_ANDAMENTO);
        repository.save(pedidos);
        return modelMapper.map(pedidos, PedidosDto.class);

    }

    public PedidosDto atualizarPedido(Long id, PedidosDto dto){
        Pedido pedidos = modelMapper.map(dto, Pedido.class);
        pedidos.setId(id);
        pedidos = repository.save(pedidos);
        return modelMapper.map(pedidos, PedidosDto.class);
    }

    public void excluirPedido(Long id){
        repository.deleteById(id);
    }
}
