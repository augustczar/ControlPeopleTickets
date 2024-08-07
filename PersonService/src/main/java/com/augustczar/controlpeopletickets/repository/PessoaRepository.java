package com.augustczar.controlpeopletickets.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.augustczar.controlpeopletickets.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, UUID>{
	
	Optional<Pessoa> findByCpf(String cpf);

}
