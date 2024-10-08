package com.augustczar.controlpeopletickets.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.augustczar.controlpeopletickets.enums.StatusBoleto;
import com.augustczar.controlpeopletickets.model.Boleto;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, UUID> {
    
	List<Boleto> findByPessoaIdOrderByDataVencimentoAsc(UUID pessoaId);
    
	List<Boleto> findByStatus(StatusBoleto status);

	Optional<Boleto> findBoletosByPessoaId(UUID pessoaId);

	List<Boleto> findByPessoaId(UUID pessoaId);
}
