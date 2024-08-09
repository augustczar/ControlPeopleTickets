package com.augustczar.controlpeopletickets.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
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
public class BoletoPagamentoDto implements Serializable {

    private static final long serialVersionUID = 2094608181654159214L;

	@NotNull
    private UUID boletoId;

    @NotNull
    @Positive
    private BigDecimal valorPago;

    @NotNull
    @PastOrPresent
    private LocalDate dataPagamento;

}

