package br.com.saboresconectados.pedidos.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.saboresconectados.pedidos.dto.ItemPedidoDto;
import br.com.saboresconectados.pedidos.model.ItemDoPedido;
import br.com.saboresconectados.pedidos.repository.ItemPedidoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemPedidoService {
    

    @Autowired
    private ItemPedidoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ItemPedidoDto> obterTodos(Pageable paginacao){
        return repository.findAll(paginacao).map(p -> modelMapper.map(paginacao, ItemPedidoDto.class));
    }

    public ItemPedidoDto obterPorID(Long id){
        ItemDoPedido itemPedido = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(itemPedido, ItemPedidoDto.class);
    }

    public ItemPedidoDto criarItPedido(ItemPedidoDto dto){
        ItemDoPedido itemPedido = modelMapper.map(dto, ItemDoPedido.class);
        repository.save(itemPedido);
        return modelMapper.map(itemPedido, ItemPedidoDto.class);
    }

    public ItemPedidoDto atualizarItemPedido(Long id, ItemPedidoDto dto){
        ItemDoPedido itemPedido = modelMapper.map(dto, ItemDoPedido.class);
        itemPedido.setId(id);
        itemPedido = repository.save(itemPedido);
        return modelMapper.map(itemPedido, ItemPedidoDto.class);
    } 

    public void excluirItem(Long id){
        repository.deleteById(id);
    }
}
