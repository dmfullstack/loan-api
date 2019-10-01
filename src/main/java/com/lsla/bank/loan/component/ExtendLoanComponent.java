package com.lsla.bank.loan.component;

import com.lsla.bank.loan.db.LoanDAO;
import com.lsla.bank.loan.db.LoanEntity;
import com.lsla.bank.parameter.ParameterName;
import com.lsla.bank.parameter.ParameterService;
import com.lsla.bank.common.wrapper.term.Term;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExtendLoanComponent {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private LoanDAO loanDAO;

    @Autowired
    private CalculateDateComponent calculateDateComponent;

    @Autowired
    private ParameterService parameterService;

    public LoanEntity extendLoan(final Long id) {
        LOGGER.debug("Extending loan with id={}", id);
        LoanEntity loanEntity = (LoanEntity) loanDAO.get(id);

        if (loanEntity == null) {
            return null;
        }
        Term term = new Term(parameterService.getParameterAsInt(ParameterName.TERM_EXTEND));
        Date extendDate = calculateDateComponent.addDays(term, loanEntity.getDueDate());

        LoanEntity updatedEntity = loanDAO.update(new LoanEntity(id, extendDate, loanEntity.getTotalAmount()));
        return updatedEntity;
    }
}
