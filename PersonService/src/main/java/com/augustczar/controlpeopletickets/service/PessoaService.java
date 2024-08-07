package com.augustczar.controlpeopletickets.service;

import java.util.List;
import java.util.UUID;

import com.augustczar.controlpeopletickets.dto.PessoaDto;

public interface PessoaService {

    PessoaDto criarPessoa(PessoaDto pessoaDto);
    List<PessoaDto> listarPessoas();
    PessoaDto buscarPessoaPorId(UUID id);
    PessoaDto atualizarPessoa(UUID id, PessoaDto pessoaDto);
    //void excluirPessoa(UUID id);
}
