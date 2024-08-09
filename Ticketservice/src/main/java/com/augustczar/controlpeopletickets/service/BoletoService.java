package com.augustczar.controlpeopletickets.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.augustczar.controlpeopletickets.dto.BoletoDto;
import com.augustczar.controlpeopletickets.dto.BoletoPagamentoDto;

public interface BoletoService {
	
    BoletoDto criarBoleto(BoletoDto boletoDto);
    
    BoletoDto buscarBoletoPorId(UUID id);
    
    BoletoDto pagarBoleto(UUID id, BigDecimal valorPago);
    
    void excluirBoleto(UUID id);
    
    List<BoletoDto> findBoletosByPessoaId(UUID pessoaId);

    BoletoDto pagarBoleto(BoletoPagamentoDto pagamentoDto);
}
