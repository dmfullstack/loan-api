package com.lsla.bank.common.validator;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class for run all validates
 */
@Component
public class ValidatorRunner {
    public ValidatorError validate(List<Validator> validators) {
        for (Validator validator : validators) {
            ValidatorError error = validator.isValid();
            if (!error.equals(ValidatorError.ok())) {
                return error;
            }
        }
        return ValidatorError.ok();
    }
}
