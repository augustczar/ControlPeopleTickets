package com.augustczar.controlpeopletickets.client;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.augustczar.controlpeopletickets.dto.BoletoDto;

//@FeignClient(name = "boleto-service", url = "${personService.ticket.service.url}")
public interface BoletoClient {

    @GetMapping("/boletos/pessoa/{pessoaId}")
    List<BoletoDto> getBoletosByPessoaId(@PathVariable("pessoaId") UUID pessoaId);

    @GetMapping("/api/boletos/{id}")
    BoletoDto buscarBoletoPorId(@PathVariable("id") UUID id);
}

