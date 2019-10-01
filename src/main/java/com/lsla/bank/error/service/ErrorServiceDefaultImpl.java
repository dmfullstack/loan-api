package com.lsla.bank.error.service;

import com.lsla.bank.common.validator.ValidatorError;
import com.lsla.bank.error.db.ErrorEntity;
import org.springframework.stereotype.Service;

@Service
public class ErrorServiceDefaultImpl implements ErrorService{

    public ErrorEntity getEntity(final ValidatorError error) {
        return new ErrorEntity(new Long(error.getErrorCode()), error.getErrorCode(), error.getErrorDescription());
    }
}
