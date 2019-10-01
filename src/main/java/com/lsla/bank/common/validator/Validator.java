package com.lsla.bank.common.validator;

import com.lsla.bank.error.ErrorRepresentation;

public interface Validator {

    public ValidatorError isValid();

    public default ValidatorError ok() {
        return ValidatorError.ok();
    }

    public default ValidatorError error(final ErrorRepresentation errorRepresentation) {
        return new ValidatorError(errorRepresentation);
    }
}
