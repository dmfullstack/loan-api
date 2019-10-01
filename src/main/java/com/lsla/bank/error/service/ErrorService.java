package com.lsla.bank.error.service;

import com.lsla.bank.common.validator.ValidatorError;
import com.lsla.bank.error.db.ErrorEntity;

public interface ErrorService {
    public ErrorEntity getEntity(final ValidatorError error);
}
