package com.augustczar.controlpeopletickets.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.augustczar.controlpeopletickets.enums.StatusBoleto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
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
public class Boleto {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID pessoaId;

    @DecimalMin("0.01")
    @Column(nullable = false)
    private BigDecimal valorDocumento;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    private BigDecimal valorPago;
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusBoleto status;

}
