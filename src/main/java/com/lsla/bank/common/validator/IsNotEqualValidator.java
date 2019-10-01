package com.lsla.bank.common.validator;

import com.lsla.bank.error.ErrorRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validate is two object are not equals.
 */
public class IsNotEqualValidator implements Validator {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private Object first;
    private Object second;

    public IsNotEqualValidator(final Object first, final Object second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ValidatorError isValid() {
        final boolean result = !first.equals(second);
        LOGGER.debug("Validating {} is not equal to {}, result={}", first, second, result);
        return result ? ValidatorError.ok() : new ValidatorError(ErrorRepresentation.IS_NOT_EQUALS_VALIDATOR_ERROR);
    }
}
