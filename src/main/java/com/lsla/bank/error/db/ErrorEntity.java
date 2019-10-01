package com.lsla.bank.error.db;

import com.lsla.bank.common.db.Entity;

public class ErrorEntity implements Entity{
    private Long id;
    private int code;
    private String description;

    public ErrorEntity(Long id, int code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
