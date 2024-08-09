package com.augustczar.controlpeopletickets.controller;

import com.augustczar.controlpeopletickets.dto.BoletoDto;
import com.augustczar.controlpeopletickets.dto.BoletoPagamentoDto;
import com.augustczar.controlpeopletickets.service.BoletoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BoletoControllerTest {

    @Mock
    private BoletoService boletoService;

    @InjectMocks
    private BoletoController boletoController;

    public BoletoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarBoleto() {
        BoletoDto boletoDto = new BoletoDto();
        BoletoDto createdBoletoDto = new BoletoDto();
        
        when(boletoService.criarBoleto(boletoDto)).thenReturn(createdBoletoDto);
        
        ResponseEntity<BoletoDto> response = boletoController.criarBoleto(boletoDto);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdBoletoDto, response.getBody());
        verify(boletoService, times(1)).criarBoleto(boletoDto);
    }

    @Test
    public void testBuscarBoletoPorId() {
        UUID id = UUID.randomUUID();
        BoletoDto boletoDto = new BoletoDto();
        
        when(boletoService.buscarBoletoPorId(id)).thenReturn(boletoDto);
        
        ResponseEntity<BoletoDto> response = boletoController.buscarBoletoPorId(id);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(boletoDto, response.getBody());
        verify(boletoService, times(1)).buscarBoletoPorId(id);
    }

    @Test
    public void testPagarBoleto() {
        BoletoPagamentoDto pagamentoDto = new BoletoPagamentoDto();
        BoletoDto boletoPagoDto = new BoletoDto();
        
        when(boletoService.pagarBoleto(pagamentoDto)).thenReturn(boletoPagoDto);
        
        ResponseEntity<BoletoDto> response = boletoController.pagarBoleto(pagamentoDto);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(boletoPagoDto, response.getBody());
        verify(boletoService, times(1)).pagarBoleto(pagamentoDto);
    }

    @Test
    public void testExcluirBoleto() {
        UUID id = UUID.randomUUID();
        
        doNothing().when(boletoService).excluirBoleto(id);
        
        ResponseEntity<Void> response = boletoController.excluirBoleto(id);
        
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(boletoService, times(1)).excluirBoleto(id);
    }

    @Test
    public void testGetBoletosByPessoaId() {
        UUID pessoaId = UUID.randomUUID();
        List<BoletoDto> boletos = Arrays.asList(new BoletoDto(), new BoletoDto());
        
        when(boletoService.findBoletosByPessoaId(pessoaId)).thenReturn(boletos);
        
        ResponseEntity<List<BoletoDto>> response = boletoController.getBoletosByPessoaId(pessoaId);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(boletos, response.getBody());
        verify(boletoService, times(1)).findBoletosByPessoaId(pessoaId);
    }
}
