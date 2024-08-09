package com.augustczar.controlpeopletickets.utils.validations;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class MaioridadeValidator implements ConstraintValidator<Maioridade, LocalDate> {

    @Override
    public void initialize(Maioridade constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate dataNascimento, ConstraintValidatorContext context) {
        if (dataNascimento == null) {
            return false;
        }
        
        // Calcula a idade baseada na data de nascimento e na data atual
        return Period.between(dataNascimento, LocalDate.now()).getYears() >= 18;
    }
}

