package br.com.saboresconectados.pedidos.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.saboresconectados.pedidos.dto.ItemPedidoDto;
import br.com.saboresconectados.pedidos.service.ItemPedidoService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {
    

    @Autowired
    private ItemPedidoService service;

    @GetMapping()
    public Page<ItemPedidoDto> listar(@PageableDefault(size = 10) Pageable paginacao){
        return service.obterTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDto> detalhar(@PathVariable @Nonnull Long id) {
        ItemPedidoDto dto = service.obterPorID(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<ItemPedidoDto> cadastrar(@RequestBody @Valid 
                                                 ItemPedidoDto  dto,
                                                 UriComponentsBuilder uriBuilder) {
        ItemPedidoDto itemPedido = service.criarItPedido(dto);
        URI endereco = uriBuilder.path("/itemPeddo/{id}").buildAndExpand(itemPedido.getId()).toUri();
        return ResponseEntity.created(endereco).body(itemPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoDto> atualizar(@PathVariable @Nonnull Long id, 
                                                  @RequestBody @Valid ItemPedidoDto dto){

        ItemPedidoDto atualizado = service.atualizarItemPedido(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemPedidoDto> remover(@PathVariable @NotNull Long id){
        service.excluirItem(id);
        return ResponseEntity.noContent().build();
    }
}
