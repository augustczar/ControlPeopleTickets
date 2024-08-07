package com.augustczar.controlpeopletickets.utils;

import org.springframework.beans.BeanUtils;

import com.augustczar.controlpeopletickets.dto.PessoaDto;
import com.augustczar.controlpeopletickets.model.Pessoa;

public class ConverterDtos {

    public static Pessoa toEntity(PessoaDto pessoaDto) {
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return pessoa;
    }

    public static PessoaDto toDto(Pessoa pessoa) {
        PessoaDto pessoaDto = new PessoaDto();
        BeanUtils.copyProperties(pessoa, pessoaDto);
        return pessoaDto;
    }
}
