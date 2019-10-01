package com.lsla.bank.common.validator;

import com.lsla.bank.error.ErrorRepresentation;

/**
 * Class for handling information about validation errors.
 */
public class ValidatorError {
    private int errorCode;
    private String errorDescription;

    public ValidatorError(final int errorCode, final String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public ValidatorError(final ErrorRepresentation errorRepresentation) {
        this.errorCode = errorRepresentation.getCode();
        this.errorDescription = errorRepresentation.getDescription();
    }

    public static ValidatorError ok() {
        return new ValidatorError(ErrorRepresentation.NO_ERROR);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ValidatorError
                && ((ValidatorError) obj).errorCode == this.errorCode
                && ((ValidatorError) obj).errorDescription.equals(this.errorDescription));
    }

    @Override
    public String toString() {
        return "ValidationError [errorCode=" + errorCode + ", errorDescription= " + errorDescription + "]";
    }
}
