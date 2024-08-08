package com.augustczar.controlpeopletickets.utils.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Component
@Constraint(validatedBy = MaioridadeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Maioridade {
    String message() default "A pessoa deve ter no mínimo 18 anos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

