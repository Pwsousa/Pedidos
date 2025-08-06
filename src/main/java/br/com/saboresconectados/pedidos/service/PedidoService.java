package br.com.saboresconectados.pedidos.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.saboresconectados.pedidos.dto.PedidosDto;
import br.com.saboresconectados.pedidos.dto.StatusDto;
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

    public List<PedidosDto> obterTodos() {
        return repository.findAll().stream()
                .map(p -> modelMapper.map(p, PedidosDto.class))
                .collect(Collectors.toList());
    }

    public PedidosDto obterPorId(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(pedido, PedidosDto.class);
    }

    public PedidosDto criarPedido(PedidosDto dto) {
        Pedido pedido = modelMapper.map(dto, Pedido.class);

        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(Status.REALIZADO);
        pedido.getItens().forEach(item -> item.setPedido(pedido));
        Pedido salvo = repository.save(pedido);

        return modelMapper.map(pedido, PedidosDto.class);
    }

    public PedidosDto atualizaStatus(Long id, StatusDto dto) {

        Pedido pedido = repository.porIdComItens(id);

        if (pedido == null) {
            throw new EntityNotFoundException();
        }

        pedido.setStatus(dto.getStatus());
        repository.atualizaStatus(dto.getStatus(), pedido);
        return modelMapper.map(pedido, PedidosDto.class);
    }

    public void aprovaPagamentoPedido(Long id) {

        Pedido pedido = repository.porIdComItens(id);

        if (pedido == null) {
            throw new EntityNotFoundException();
        }

        pedido.setStatus(Status.PAGO);
        repository.atualizaStatus(Status.PAGO, pedido);
    }
}
