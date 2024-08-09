package com.augustczar.controlpeopletickets.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augustczar.controlpeopletickets.dto.BoletoDto;
import com.augustczar.controlpeopletickets.dto.BoletoPagamentoDto;
import com.augustczar.controlpeopletickets.enums.StatusBoleto;
import com.augustczar.controlpeopletickets.model.Boleto;
import com.augustczar.controlpeopletickets.repository.BoletoRepository;
import com.augustczar.controlpeopletickets.service.BoletoService;
import com.augustczar.controlpeopletickets.utils.ConverterDtos;

import jakarta.transaction.Transactional;

@Service
public class BoletoServiceImpl implements BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    @Override
    public BoletoDto criarBoleto(BoletoDto boletoDto) {
        Boleto boleto = ConverterDtos.toEntity(boletoDto);
        boletoRepository.save(boleto);
        return ConverterDtos.toDto(boleto);
    }

    @Override
    public BoletoDto buscarBoletoPorId(UUID id) {
        Boleto boleto = boletoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Boleto não encontrado com ID: " + id));
        return ConverterDtos.toDto(boleto);
    }

    @Override
    public BoletoDto pagarBoleto(UUID id, BigDecimal valorPago) {
        Boleto boleto = boletoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Boleto não encontrado com ID: " + id));

        if (boleto.getStatus() != StatusBoleto.PENDENTE) {
            throw new IllegalStateException("Apenas boletos pendentes podem ser pagos.");
        }

        if (boleto.getValorDocumento().compareTo(valorPago) != 0) {
            throw new IllegalArgumentException("O valor pago deve ser igual ao valor do documento.");
        }

        boleto.setValorPago(valorPago);
        boleto.setDataPagamento(LocalDate.now());
        boleto.setStatus(StatusBoleto.PAGO);
        boletoRepository.save(boleto);

        return ConverterDtos.toDto(boleto);
    }

    @Override
    public void excluirBoleto(UUID id) {
        Boleto boleto = boletoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Boleto não encontrado com ID: " + id));

        if (boleto.getStatus() == StatusBoleto.PAGO) {
            throw new IllegalStateException("Não é possível excluir um boleto pago.");
        }

        boletoRepository.delete(boleto);
    }

	@Override
	public List<BoletoDto> findBoletosByPessoaId(UUID pessoaId) {
		List<Boleto> boletos = boletoRepository.findByPessoaIdOrderByDataVencimentoAsc(pessoaId);

		if (boletos.isEmpty()) {
			throw new NoSuchElementException("Boletos não encontrados para o ID da pessoa: " + pessoaId);
		}

		// Convertendo a lista de Boleto para BoletoDto
		return boletos.stream().map(ConverterDtos::toDto).collect(Collectors.toList());
	}

    @Override
    @Transactional
	public BoletoDto pagarBoleto(BoletoPagamentoDto pagamentoDto) {
        Boleto boleto = boletoRepository.findById(pagamentoDto.getBoletoId())
                .orElseThrow(() -> new NoSuchElementException("Boleto não encontrado"));

        // Verifica se o boleto está pendente
        if (!boleto.getStatus().equals(StatusBoleto.PENDENTE)) {
            throw new IllegalStateException("Somente boletos com status 'Pendente' podem ser pagos");
        }

        // Verifica se o valor pago é igual ao valor do documento
        if (pagamentoDto.getValorPago().compareTo(boleto.getValorDocumento()) != 0) {
            throw new IllegalArgumentException("O valor pago deve ser igual ao valor do documento");
        }

        // Atualiza as informações de pagamento
        boleto.setValorPago(pagamentoDto.getValorPago());
        boleto.setDataPagamento(pagamentoDto.getDataPagamento());
        boleto.setStatus(StatusBoleto.PAGO);

        boletoRepository.save(boleto);

        // Converte para DTO e retorna
        return ConverterDtos.toDto(boleto);
    }
}

