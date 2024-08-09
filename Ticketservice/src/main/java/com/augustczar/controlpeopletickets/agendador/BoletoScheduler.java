package com.augustczar.controlpeopletickets.agendador;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.augustczar.controlpeopletickets.enums.StatusBoleto;
import com.augustczar.controlpeopletickets.model.Boleto;
import com.augustczar.controlpeopletickets.repository.BoletoRepository;

@Service
public class BoletoScheduler {

    @Autowired
    private BoletoRepository boletoRepository;

    @Scheduled(cron = "0 0 1 * * *")
    public void atualizarBoletosVencidos() {
        List<Boleto> boletosPendentes = boletoRepository.findByStatus(StatusBoleto.PENDENTE);
        boletosPendentes.forEach(boleto -> {
            if (boleto.getDataVencimento().isBefore(LocalDate.now())) {
                boleto.setStatus(StatusBoleto.VENCIDO);
                boletoRepository.save(boleto);
            }
        });
    }
}

