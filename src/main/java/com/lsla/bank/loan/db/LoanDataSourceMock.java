package com.lsla.bank.loan.db;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoanDataSourceMock {
    private static Long lastId = 1L;
    private Map<Long, LoanEntity> loanEntityMap = new HashMap<>();

    public LoanEntity save(final LoanEntity loanEntity) {
        final LoanEntity newLoan = new LoanEntity(lastId, loanEntity.getDueDate(), loanEntity.getTotalAmount());
        loanEntityMap.put(lastId, newLoan);
        ++lastId;
        return newLoan;
    }

    public LoanEntity get(final Long id) {
        return loanEntityMap.get(id);
    }

    public LoanEntity update(LoanEntity loanEntity) {
        loanEntityMap.put(loanEntity.getId(), loanEntity);
        return loanEntity;
    }
}
