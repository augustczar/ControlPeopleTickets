package com.augustczar.controlpeopletickets.utils;

import org.springframework.beans.BeanUtils;

import com.augustczar.controlpeopletickets.dto.BoletoDto;
import com.augustczar.controlpeopletickets.model.Boleto;

public class ConverterDtos {

    public static Boleto toEntity(BoletoDto boletoDto) {
        Boleto boleto = new Boleto();
        BeanUtils.copyProperties(boletoDto, boleto);
        return boleto;
    }

    public static BoletoDto toDto(Boleto boleto) {
        BoletoDto boletoDto = new BoletoDto();
        BeanUtils.copyProperties(boleto, boletoDto);
        if (boleto.getPessoaId() != null) {
            boletoDto.setPessoaId(boleto.getPessoaId());
        }
        return boletoDto;
    }
}
