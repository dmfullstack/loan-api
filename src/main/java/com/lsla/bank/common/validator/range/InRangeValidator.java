package com.lsla.bank.common.validator.range;

import com.lsla.bank.common.validator.Validator;
import com.lsla.bank.common.validator.ValidatorError;
import com.lsla.bank.error.ErrorRepresentation;
import com.lsla.bank.common.wrapper.range.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validate is actual value are in range
 */
public class InRangeValidator implements Validator {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private Range range;
    private ErrorRepresentation errorRepresentation;

    public InRangeValidator(final Range range, final ErrorRepresentation errorRepresentation) {
        this.range = range;
        this.errorRepresentation = errorRepresentation;
    }

    public ValidatorError isValid() {
        final boolean result =  range.isInRange();
        LOGGER.debug("Validating is range: {}, result: {}", range, result);
        return result ? ValidatorError.ok() : error(errorRepresentation);
    }
}
