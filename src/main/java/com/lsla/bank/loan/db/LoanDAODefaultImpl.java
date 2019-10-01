package com.lsla.bank.loan.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoanDAODefaultImpl implements LoanDAO{

    @Autowired
    private LoanDataSourceMock data;

    public LoanEntity get(final Long id) {
        return data.get(id);
    }

    public LoanEntity save(final LoanEntity loanEntity){
        return data.save(loanEntity);
    }

    public LoanEntity update(final LoanEntity loanEntity) {
        return data.update(loanEntity);
    }

}
