package com.augustczar.controlpeopletickets.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity
public class Pessoa implements Serializable {
    
	private static final long serialVersionUID = -7254517045264318592L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    @Column(unique = true)
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

}

