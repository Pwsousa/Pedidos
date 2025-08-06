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

import br.com.saboresconectados.pedidos.dto.PedidosDto;
import br.com.saboresconectados.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;




@RestController
@RequestMapping("/pedidos")
public class PedidosController {
    
    @Autowired
    private PedidoService service;

    @GetMapping() 
    public Page<PedidosDto> listar(@PageableDefault(size = 10) Pageable paginacao){
        return service.obterTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidosDto> detalhar(@PathVariable @NotNull Long id) {
        PedidosDto dto = service.obterPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<PedidosDto> cadastrar(@RequestBody @Valid 
                                                 PedidosDto dto,
                                                 UriComponentsBuilder uriBuilder) {
        PedidosDto pedido = service.criarPedido(dto);
        URI endereco = uriBuilder
                        .path("/pedidos/{id}")
                        .buildAndExpand(pedido.getId())
                        .toUri();
        return ResponseEntity.created(endereco).body(pedido);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PedidosDto> atualizar(@PathVariable @NotNull Long id, 
                                                  @RequestBody @Valid PedidosDto dto){

        PedidosDto atualizado = service.atualizarPedido(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PedidosDto> remover(@PathVariable @NotNull Long id){
        service.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }
}
