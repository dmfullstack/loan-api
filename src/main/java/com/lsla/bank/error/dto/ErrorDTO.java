package com.lsla.bank.error.dto;

import com.lsla.bank.common.dto.Message;

public class ErrorDTO implements Message {
    private int code;
    private String description;

    public ErrorDTO(final int code, final String description) {
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
