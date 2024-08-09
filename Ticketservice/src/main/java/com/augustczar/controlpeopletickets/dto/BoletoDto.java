package com.augustczar.controlpeopletickets.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.augustczar.controlpeopletickets.enums.StatusBoleto;

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
public class BoletoDto implements Serializable {

    private static final long serialVersionUID = -927711202115858250L;
    
	private UUID id;
	
    private UUID pessoaId;
    
    private BigDecimal valorDocumento;
    
    private LocalDate dataVencimento;
    
    private BigDecimal valorPago;
    
    private LocalDate dataPagamento;
    
    private StatusBoleto status;
    
}
