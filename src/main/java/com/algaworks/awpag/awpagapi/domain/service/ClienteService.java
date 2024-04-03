package com.algaworks.awpag.awpagapi.domain.service;

import com.algaworks.awpag.awpagapi.domain.exception.NegocioException;
import com.algaworks.awpag.awpagapi.domain.model.Cliente;
import com.algaworks.awpag.awpagapi.domain.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente){
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .filter(c -> !c.equals(cliente))
                .isPresent();
        if(emailEmUso){
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
