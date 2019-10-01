package com.lsla.bank.common.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Run two validators and if both failed, return error
 */
public class NandValidator implements Validator {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private List<Validator> validatorList = new ArrayList<>();

    private Validator first;
    private Validator second;
    private ValidatorError error;

    public NandValidator(final Validator first, final Validator second, final ValidatorError error) {
        this.first = first;
        this.second = second;
        this.error = error;
    }

    @Override
    public ValidatorError isValid() {
        final ValidatorError firstValidation = first.isValid();
        final ValidatorError secondValidation = second.isValid();
        final boolean result = !firstValidation.equals(ValidatorError.ok()) && !secondValidation.equals(ValidatorError.ok());
        LOGGER.debug("And validation, first={}, second={}, result={}", firstValidation, secondValidation, result);
        return result ? error : ValidatorError.ok();

    }

}
