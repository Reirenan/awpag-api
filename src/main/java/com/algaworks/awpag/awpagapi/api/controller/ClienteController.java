package com.algaworks.awpag.awpagapi.api.controller;

import com.algaworks.awpag.awpagapi.domain.model.Cliente;
import com.algaworks.awpag.awpagapi.domain.repository.ClienteRepository;
import com.algaworks.awpag.awpagapi.domain.service.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class ClienteController {

    private final ClienteService cadastroClienteService;
    private final ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @GetMapping("clientes/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/clientes")
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return cadastroClienteService.salvar(cliente);
    }

    @PutMapping("/clientes/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        cliente = cadastroClienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{clienteId}")
    public ResponseEntity<Cliente> excluir(@PathVariable Long clienteId){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(clienteId);
        return ResponseEntity.noContent().build();
    }


}
