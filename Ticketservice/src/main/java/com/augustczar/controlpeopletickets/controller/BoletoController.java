package com.augustczar.controlpeopletickets.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augustczar.controlpeopletickets.dto.BoletoDto;
import com.augustczar.controlpeopletickets.dto.BoletoPagamentoDto;
import com.augustczar.controlpeopletickets.service.BoletoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/boletos")
public class BoletoController {

	@Autowired
    private BoletoService boletoService;

    @PostMapping("/criar")
    public ResponseEntity<BoletoDto> criarBoleto(@Valid @RequestBody BoletoDto boletoDto) {
        BoletoDto novoBoleto = boletoService.criarBoleto(boletoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoBoleto);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<BoletoDto> buscarBoletoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(boletoService.buscarBoletoPorId(id));
    }

    @PostMapping("/pagar")
    public ResponseEntity<BoletoDto> pagarBoleto(@Valid @RequestBody BoletoPagamentoDto pagamentoDto) {
        BoletoDto boletoPago = boletoService.pagarBoleto(pagamentoDto);
        return ResponseEntity.ok(boletoPago);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirBoleto(@PathVariable UUID id) {
        boletoService.excluirBoleto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<BoletoDto>> getBoletosByPessoaId(@PathVariable UUID pessoaId) {
        return ResponseEntity.ok(boletoService.findBoletosByPessoaId(pessoaId));
    }
}

