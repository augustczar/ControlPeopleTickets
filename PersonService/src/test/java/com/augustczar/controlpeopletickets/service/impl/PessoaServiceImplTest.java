package com.augustczar.controlpeopletickets.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.augustczar.controlpeopletickets.client.BoletoClient;
import com.augustczar.controlpeopletickets.dto.BoletoDto;
import com.augustczar.controlpeopletickets.dto.PessoaDto;
import com.augustczar.controlpeopletickets.model.Pessoa;
import com.augustczar.controlpeopletickets.repository.PessoaRepository;
import com.augustczar.controlpeopletickets.utils.ConverterDtos;

class PessoaServiceImplTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private BoletoClient boletoClient;

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Carla Dias");

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaDto pessoaDto = ConverterDtos.toDto(pessoa);

        PessoaDto result = pessoaService.criarPessoa(pessoaDto);

        assertNotNull(result);
        assertEquals("Carla Dias", result.getNome());
    }

    @Test
    void testListarPessoas() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Carla Dias");

        when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));

        List<PessoaDto> result = pessoaService.listarPessoas();

        assertEquals(1, result.size());
        assertEquals("Carla Dias", result.get(0).getNome());
    }

    @Test
    void testBuscarPessoaPorId() {
        UUID id = UUID.randomUUID();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Carla Dias");

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));

        PessoaDto result = pessoaService.buscarPessoaPorId(id);

        assertNotNull(result);
        assertEquals("Carla Dias", result.getNome());
    }

    @Test
    void testAtualizarPessoa() {
        UUID id = UUID.randomUUID();
        Pessoa pessoaExistente = new Pessoa();
        pessoaExistente.setId(id);
        pessoaExistente.setNome("Carla Dias");

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaExistente));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaExistente);

        PessoaDto pessoaDto = ConverterDtos.toDto(pessoaExistente);
        PessoaDto result = pessoaService.atualizarPessoa(id, pessoaDto);

        assertNotNull(result);
        assertEquals("Carla Dias", result.getNome());
    }

    @Test
    void testExcluirPessoa() {
        UUID id = UUID.randomUUID();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));
        when(boletoClient.getBoletosByPessoaId(id)).thenReturn(List.of());

        doNothing().when(pessoaRepository).delete(pessoa);

        pessoaService.excluirPessoa(id);

        verify(pessoaRepository, times(1)).delete(pessoa);
    }

    @Test
    void testBuscarPessoaComBoletos() {
        UUID id = UUID.randomUUID();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoa.setNome("Carla Dias");

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));
        when(boletoClient.getBoletosByPessoaId(id)).thenReturn(List.of(new BoletoDto()));

        PessoaDto result = pessoaService.buscarPessoaComBoletos(id);

        assertNotNull(result);
        assertEquals("Carla Dias", result.getNome());
        assertEquals(1, result.getBoletos().size());
    }
}
