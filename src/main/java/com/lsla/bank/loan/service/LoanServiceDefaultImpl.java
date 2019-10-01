package com.lsla.bank.loan.service;

import com.lsla.bank.common.db.Entity;
import com.lsla.bank.loan.component.ApplyForLoanComponent;
import com.lsla.bank.loan.component.ExtendLoanComponent;
import com.lsla.bank.loan.db.LoanDAO;
import com.lsla.bank.loan.dto.LoanRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoanServiceDefaultImpl implements LoanService {

    @Autowired
    private ApplyForLoanComponent applyForLoanComponent;

    @Autowired
    private ExtendLoanComponent extendLoanComponent;

    @Autowired
    private LoanDAO loanDAO;

    @Override
    public Entity applyForLoan(final LoanRequestDTO requestDTO, final Date date)  {
        return applyForLoanComponent.applyForLoan(requestDTO, date);
    }

    @Override
    public Entity getLoan(Long id) {
        return loanDAO.get(id);
    }

    @Override
    public Entity extendLoan(Long id) {
        return extendLoanComponent.extendLoan(id);
    }

}
