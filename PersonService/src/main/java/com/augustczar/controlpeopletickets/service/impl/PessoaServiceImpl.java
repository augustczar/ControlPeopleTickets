package com.augustczar.controlpeopletickets.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augustczar.controlpeopletickets.dto.PessoaDto;
import com.augustczar.controlpeopletickets.model.Pessoa;
import com.augustczar.controlpeopletickets.repository.PessoaRepository;
import com.augustczar.controlpeopletickets.service.PessoaService;
import com.augustczar.controlpeopletickets.utils.ConverterDtos;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public PessoaDto criarPessoa(PessoaDto pessoaDto) {
        Pessoa pessoa = ConverterDtos.toEntity(pessoaDto);
        pessoaRepository.save(pessoa);
        return ConverterDtos.toDto(pessoa);
    }

    @Override
    public List<PessoaDto> listarPessoas() {
        return pessoaRepository.findAll().stream()
                .map(ConverterDtos::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PessoaDto buscarPessoaPorId(UUID id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pessoa não encontrada com ID: " + id));
        return ConverterDtos.toDto(pessoa);
    }

    @Override
    public PessoaDto atualizarPessoa(UUID id, PessoaDto pessoaDto) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pessoa não encontrada com ID: " + id));
        Pessoa pessoaAtualizada = ConverterDtos.toEntity(pessoaDto);
        pessoaAtualizada.setId(pessoaExistente.getId());
        pessoaRepository.save(pessoaAtualizada);
        return ConverterDtos.toDto(pessoaAtualizada);
    }
}
