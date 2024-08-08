package com.augustczar.controlpeopletickets.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
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
    
    @NotNull
	private Long id;
    
    @NotBlank
    private String nome;
 
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String cpf;
    
    @NotNull
    @Past
    @Min(18)
    private LocalDate dataNascimento;
    
    private String cep;
    
    private String logradouro;
    
    private String bairro;
    
    private String uf;
    
    private String cidade;

    private List<BoletoDto> boletos;
}

