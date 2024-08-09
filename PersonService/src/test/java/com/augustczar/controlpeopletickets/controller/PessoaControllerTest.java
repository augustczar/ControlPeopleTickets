package com.augustczar.controlpeopletickets.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.augustczar.controlpeopletickets.dto.PessoaDto;
import com.augustczar.controlpeopletickets.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;

class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
    }

    @Test
    void testCriarPessoa() throws Exception {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Carla Dias");

        when(pessoaService.criarPessoa(any(PessoaDto.class))).thenReturn(pessoaDto);

        mockMvc.perform(post("/pessoas/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pessoaDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Carla Dias"));
    }

    @Test
    void testListarPessoas() throws Exception {
        when(pessoaService.listarPessoas()).thenReturn(Arrays.asList(new PessoaDto(), new PessoaDto()));

        mockMvc.perform(get("/pessoas/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testBuscarPessoaPorId() throws Exception {
        UUID id = UUID.randomUUID();
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Carla Dias");

        when(pessoaService.buscarPessoaPorId(id)).thenReturn(pessoaDto);

        mockMvc.perform(get("/pessoas/bucar/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Carla Dias"));
    }

    @Test
    void testAtualizarPessoa() throws Exception {
        UUID id = UUID.randomUUID();
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Carla Dias");

        when(pessoaService.atualizarPessoa(eq(id), any(PessoaDto.class))).thenReturn(pessoaDto);

        mockMvc.perform(put("/pessoas/atualizar/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pessoaDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Carla Dias"));
    }

    @Test
    void testExcluirPessoa() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(pessoaService).excluirPessoa(id);

        mockMvc.perform(delete("/pessoas/excluir/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void testBuscarPessoaComBoletos() throws Exception {
        UUID id = UUID.randomUUID();
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Carla Dias");

        when(pessoaService.buscarPessoaComBoletos(id)).thenReturn(pessoaDto);

        mockMvc.perform(get("/pessoas/{pessoaId}/boletos", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Carla Dias"));
    }
}
