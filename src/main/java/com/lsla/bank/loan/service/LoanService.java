package com.lsla.bank.loan.service;

import com.lsla.bank.common.db.Entity;
import com.lsla.bank.loan.dto.LoanRequestDTO;

import java.util.Date;

public interface LoanService {
    public Entity applyForLoan(final LoanRequestDTO requestDTO, final Date date);
    public Entity getLoan(final Long id);
    public Entity extendLoan(final Long id);
}
