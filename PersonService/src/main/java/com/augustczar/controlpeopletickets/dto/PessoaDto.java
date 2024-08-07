package com.augustczar.controlpeopletickets.dto;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PessoaDto implements Serializable{
	

    private static final long serialVersionUID = 2193998997678708796L;
    
    @NotNull
	private Long id;
    
    @NotBlank
    private String nome;
 
    @NotBlank
    private String cpf;
    
    @NotNull
    private LocalDate dataNascimento;
    
    private String cep;
    private String logradouro;
    private String bairro;
    private String uf;
    private String cidade;
}

