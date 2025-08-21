package br.com.saboresconectados.pedidos.controller;

import java.net.URI;
import java.util.List;

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
import br.com.saboresconectados.pedidos.dto.StatusDto;
import br.com.saboresconectados.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;




@RestController
@RequestMapping("/pedidos")
public class PedidosController {
    
    @Autowired
    private PedidoService service;

    @GetMapping() 
    public List<PedidosDto> listar(){
        return service.obterTodos();
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


    @PutMapping("/{id}/status")
    public ResponseEntity<PedidosDto> atualizaStatus(@PathVariable Long id, @RequestBody StatusDto status){
        PedidosDto dto = service.atualizaStatus(id, status);

        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> aprovaPagamento(@PathVariable @NotNull Long id) {
        service.aprovaPagamentoPedido(id);

        return ResponseEntity.ok().build();

    }

}
