package com.algaworks.awpag.awpagapi.api.controller;

import com.algaworks.awpag.awpagapi.domain.model.Parcelamento;
import com.algaworks.awpag.awpagapi.api.model.ParcelamentoModel;
import com.algaworks.awpag.awpagapi.domain.repository.ParcelamentoRepository;
import com.algaworks.awpag.awpagapi.domain.service.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {
    private final ParcelamentoRepository parcelamentoRepository;
    private final ParcelamentoService parcelamentoService;

    @GetMapping
    public List<Parcelamento> listar(){
        return parcelamentoRepository.findAll();
    }
    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long parcelamentoId){
        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamento -> {
                    var parcelamentoModel = new ParcelamentoModel();
                    parcelamentoModel.setId(parcelamento.getId());
                    parcelamentoModel.setNomeCliente(parcelamento.getCliente().getNome());
                    parcelamentoModel.setDescricao(parcelamento.getDescricao());
                    parcelamentoModel.setValorTotal(parcelamento.getValorTotal());
                    parcelamentoModel.setParcelas(parcelamento.getQuatidade());
                    parcelamentoModel.setDataCriacao(parcelamento.getDateTime());
                    return ResponseEntity.ok(parcelamentoModel);

                })
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parcelamento cadastrar(@Valid @RequestBody Parcelamento parcelamento){
        return parcelamentoService.cadastrar(parcelamento);
    }

}
