package com.augustczar.controlpeopletickets.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.augustczar.controlpeopletickets.dto.BoletoDto;
import com.augustczar.controlpeopletickets.dto.BoletoPagamentoDto;
import com.augustczar.controlpeopletickets.enums.StatusBoleto;
import com.augustczar.controlpeopletickets.model.Boleto;
import com.augustczar.controlpeopletickets.repository.BoletoRepository;
import com.augustczar.controlpeopletickets.utils.ConverterDtos;

public class BoletoServiceImplTest {

    @Mock
    private BoletoRepository boletoRepository;

    @InjectMocks
    private BoletoServiceImpl boletoServiceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarBoleto() {
        BoletoDto boletoDto = new BoletoDto();
        Boleto boleto = new Boleto();
        
        when(ConverterDtos.toEntity(boletoDto)).thenReturn(boleto);
        when(boletoRepository.save(boleto)).thenReturn(boleto);
        when(ConverterDtos.toDto(boleto)).thenReturn(boletoDto);
        
        BoletoDto createdBoleto = boletoServiceImpl.criarBoleto(boletoDto);
        
        assertEquals(boletoDto, createdBoleto);
        verify(boletoRepository, times(1)).save(boleto);
    }

    @Test
    public void testBuscarBoletoPorId() {
        UUID id = UUID.randomUUID();
        Boleto boleto = new Boleto();
        BoletoDto boletoDto = new BoletoDto();
        
        when(boletoRepository.findById(id)).thenReturn(Optional.of(boleto));
        when(ConverterDtos.toDto(boleto)).thenReturn(boletoDto);
        
        BoletoDto foundBoleto = boletoServiceImpl.buscarBoletoPorId(id);
        
        assertEquals(boletoDto, foundBoleto);
        verify(boletoRepository, times(1)).findById(id);
    }

    @Test
    public void testBuscarBoletoPorId_NotFound() {
        UUID id = UUID.randomUUID();
        
        when(boletoRepository.findById(id)).thenReturn(Optional.empty());
        
        assertThrows(NoSuchElementException.class, () -> {
            boletoServiceImpl.buscarBoletoPorId(id);
        });
        
        verify(boletoRepository, times(1)).findById(id);
    }

    @Test
    public void testPagarBoleto() {
        UUID id = UUID.randomUUID();
        Boleto boleto = new Boleto();
        boleto.setStatus(StatusBoleto.PENDENTE);
        boleto.setValorDocumento(new BigDecimal("100.00"));
        
        when(boletoRepository.findById(id)).thenReturn(Optional.of(boleto));
        
        BoletoDto boletoPago = boletoServiceImpl.pagarBoleto(id, new BigDecimal("100.00"));
        
        assertEquals(StatusBoleto.PAGO, boleto.getStatus());
        assertEquals(new BigDecimal("100.00"), boleto.getValorPago());
        assertNotNull(boleto.getDataPagamento());
        verify(boletoRepository, times(1)).save(boleto);
    }

    @Test
    public void testExcluirBoleto() {
        UUID id = UUID.randomUUID();
        Boleto boleto = new Boleto();
        boleto.setStatus(StatusBoleto.PENDENTE);
        
        when(boletoRepository.findById(id)).thenReturn(Optional.of(boleto));
        
        boletoServiceImpl.excluirBoleto(id);
        
        verify(boletoRepository, times(1)).delete(boleto);
    }

    @Test
    public void testExcluirBoleto_Pago() {
        UUID id = UUID.randomUUID();
        Boleto boleto = new Boleto();
        boleto.setStatus(StatusBoleto.PAGO);
        
        when(boletoRepository.findById(id)).thenReturn(Optional.of(boleto));
        
        assertThrows(IllegalStateException.class, () -> {
            boletoServiceImpl.excluirBoleto(id);
        });
        
        verify(boletoRepository, times(0)).delete(boleto);
    }

    @Test
    public void testFindBoletosByPessoaId() {
        UUID pessoaId = UUID.randomUUID();
        Boleto boleto1 = new Boleto();
        Boleto boleto2 = new Boleto();
        List<Boleto> boletos = List.of(boleto1, boleto2);
        
        when(boletoRepository.findByPessoaIdOrderByDataVencimentoAsc(pessoaId)).thenReturn(boletos);
        
        List<BoletoDto> boletoDtos = boletoServiceImpl.findBoletosByPessoaId(pessoaId);
        
        assertEquals(2, boletoDtos.size());
        verify(boletoRepository, times(1)).findByPessoaIdOrderByDataVencimentoAsc(pessoaId);
    }
    
    @Test
    public void testPagarBoletoPorPagamentoDto() {
        BoletoPagamentoDto pagamentoDto = new BoletoPagamentoDto();
        pagamentoDto.setBoletoId(UUID.randomUUID());
        pagamentoDto.setValorPago(new BigDecimal("100.00"));
        
        Boleto boleto = new Boleto();
        boleto.setStatus(StatusBoleto.PENDENTE);
        boleto.setValorDocumento(new BigDecimal("100.00"));
        
        when(boletoRepository.findById(pagamentoDto.getBoletoId())).thenReturn(Optional.of(boleto));
        
        BoletoDto boletoPago = boletoServiceImpl.pagarBoleto(pagamentoDto);
        
        assertEquals(StatusBoleto.PAGO, boleto.getStatus());
        assertEquals(pagamentoDto.getValorPago(), boleto.getValorPago());
        assertNotNull(boleto.getDataPagamento());
        verify(boletoRepository, times(1)).save(boleto);
    }
}
