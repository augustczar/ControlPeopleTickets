package com.augustczar.controlpeopletickets.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.augustczar.controlpeopletickets.utils.validations.Maioridade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PessoaDto implements Serializable{
	

    private static final long serialVersionUID = 2193998997678708796L;
    
	private UUID id;
    
    @NotBlank
    private String nome;
 
    @NotBlank
    private String cpf;
    
    @Past(message = "A data de nascimento deve ser no passado")
    @Maioridade
    private LocalDate dataNascimento;
    
    private String cep;
    
    private String logradouro;
    
    private String bairro;
    
    private String uf;
    
    private String cidade;

    private List<BoletoDto> boletos;
}

