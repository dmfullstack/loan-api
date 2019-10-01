package com.lsla.bank.loan.db;

public interface LoanDAO {
    public LoanEntity get(final Long id);
    public LoanEntity save(final LoanEntity loanEntity);
    public LoanEntity update(final LoanEntity loanEntity);
}
