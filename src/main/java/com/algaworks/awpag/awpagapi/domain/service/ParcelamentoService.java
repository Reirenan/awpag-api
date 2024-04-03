package com.algaworks.awpag.awpagapi.domain.service;

import com.algaworks.awpag.awpagapi.domain.exception.NegocioException;
import com.algaworks.awpag.awpagapi.domain.model.Cliente;
import com.algaworks.awpag.awpagapi.domain.model.Parcelamento;
import com.algaworks.awpag.awpagapi.domain.repository.ClienteRepository;
import com.algaworks.awpag.awpagapi.domain.repository.ParcelamentoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {
    private final ParcelamentoRepository parcelamentoRepository;
    private final ClienteRepository clienteRepository;
    @Transactional
    public Parcelamento cadastrar(Parcelamento novoParcelamento){
        if(novoParcelamento.getId() != null){
            throw new NegocioException("Parcelamento a ser criado não deve possuir um código");
        }
        Cliente cliente = clienteRepository.findById(novoParcelamento.getCliente().getId())
                .orElseThrow(()->new NegocioException("Cliente não encontrado"));
        novoParcelamento.setCliente(cliente);
        novoParcelamento.setDateTime(OffsetDateTime.now());
        return parcelamentoRepository.save(novoParcelamento);
    }
}
