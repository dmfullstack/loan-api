package com.lsla.bank.error;

public enum ErrorRepresentation {
    NO_ERROR(0, "No error"),
    AMOUNT_VALIDATOR_ERROR(100, "Amount validator failed"),
    TERM_VALIDATOR_ERROR(101, "Term validator failed"),
    TIME_VALIDATOR_ERROR(102, "Time validator failed"),
    IS_NOT_EQUALS_VALIDATOR_ERROR(104, "Objects are not equals");


    private int code;
    private String description;

    ErrorRepresentation(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
