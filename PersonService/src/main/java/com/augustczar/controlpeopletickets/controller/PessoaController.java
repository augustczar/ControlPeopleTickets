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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augustczar.controlpeopletickets.dto.PessoaDto;
import com.augustczar.controlpeopletickets.service.PessoaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<PessoaDto> criarPessoa(@Valid @RequestBody PessoaDto pessoaDto) {
        PessoaDto novaPessoa = pessoaService.criarPessoa(pessoaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PessoaDto>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }

    @GetMapping("/bucar/{id}")
    public ResponseEntity<PessoaDto> buscarPessoaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(pessoaService.buscarPessoaPorId(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PessoaDto> atualizarPessoa(@PathVariable UUID id, @RequestBody PessoaDto pessoaDTO) {
        return ResponseEntity.ok(pessoaService.atualizarPessoa(id, pessoaDTO));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirPessoa(@PathVariable UUID id) {
        pessoaService.excluirPessoa(id);
        return ResponseEntity.noContent().build();
    }
}

