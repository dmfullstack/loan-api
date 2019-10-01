package com.lsla.bank.loan.component;

import com.lsla.bank.common.db.Entity;
import com.lsla.bank.common.validator.*;
import com.lsla.bank.common.validator.range.InRangeValidator;
import com.lsla.bank.common.validator.range.NotInRangeValidator;
import com.lsla.bank.error.ErrorRepresentation;
import com.lsla.bank.error.service.ErrorService;
import com.lsla.bank.loan.db.LoanDAO;
import com.lsla.bank.loan.db.LoanEntity;
import com.lsla.bank.loan.dto.LoanRequestDTO;
import com.lsla.bank.parameter.ParameterService;
import com.lsla.bank.common.wrapper.range.TimeRange;
import com.lsla.bank.common.wrapper.amount.Amount;
import com.lsla.bank.common.wrapper.range.AmountRange;
import com.lsla.bank.common.wrapper.term.Term;
import com.lsla.bank.common.wrapper.range.TermRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.lsla.bank.parameter.ParameterName.*;
import static com.lsla.bank.parameter.ParameterName.REJECT_TIME_TO;

/**
 * Component for login in apply for loan.
 */
@Service
public class ApplyForLoanComponent {
    @Autowired
    private ParameterService parameterService;

    @Autowired
    private LoanDAO loanDAO;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private LoanCostCalculatorComponent loanCostCalculatorComponent;

    @Autowired
    private CalculateDateComponent calculateDateComponent;

    @Autowired
    private ValidatorRunner validatorRunner;

    public Entity applyForLoan(final LoanRequestDTO requestDTO, final Date date)  {
        final Amount amountToValidate = new Amount(requestDTO.getAmount());
        final Term termToValidate = new Term(requestDTO.getTerm());
        final Date actualDate = new Date();

        ValidatorError error = validatorRunner.validate(getValidatorList(amountToValidate, termToValidate, actualDate));

        if(error.equals(ValidatorError.ok())) {
            final Amount totalAmount = loanCostCalculatorComponent.percentFromAmount(amountToValidate, parameterService.getParameterAsInt(LOAN_PERCENT_COST));
            final Date calculatedDate = calculateDateComponent.addDays(termToValidate, actualDate);

            return loanDAO.save(new LoanEntity(0L, calculatedDate, totalAmount));
        }

        return errorService.getEntity(error);
    }


    private List<Validator> getValidatorList(final Amount amount, final Term term, final Date date) {

        final AmountRange amountRange = createAmountRange(amount);
        final TermRange termRange = createTermRange(term);
        final TimeRange timeRange = createTimeRange(date);

        Validator amountValidator = new InRangeValidator(amountRange, ErrorRepresentation.AMOUNT_VALIDATOR_ERROR);
        Validator termValidator = new InRangeValidator(termRange, ErrorRepresentation.TERM_VALIDATOR_ERROR);
        Validator timeValidator = new NotInRangeValidator(timeRange, ErrorRepresentation.TIME_VALIDATOR_ERROR);

        Validator amountIsNotEqual = new IsNotEqualValidator(amount, new Amount(parameterService.getParameterAsInt(AMOUNT_MAX)));
        Validator timeAndMaxAmountValidator = new NandValidator(timeValidator, amountIsNotEqual, new ValidatorError(200, "Time range and max amount"));

        final List<Validator> validatorList = new ArrayList<>();
        validatorList.add(amountValidator);
        validatorList.add(termValidator);
        validatorList.add(timeAndMaxAmountValidator);

        System.out.println(validatorList);
        return validatorList;
    }

    private AmountRange createAmountRange(final Amount amount) {
        final Amount minAmount = new Amount(parameterService.getParameterAsInt(AMOUNT_MIN));
        final Amount maxAmount = new Amount(parameterService.getParameterAsInt(AMOUNT_MAX));
        final AmountRange amountRange = new AmountRange(amount, minAmount, maxAmount);

        return amountRange;
    }

    private TermRange createTermRange(final Term term) {
        final Term minTerm = new Term(parameterService.getParameterAsInt(TERM_DAY_MIN));
        final Term maxTerm = new Term(parameterService.getParameterAsInt(TERM_DAY_MAX));
        final TermRange termRange = new TermRange(term, minTerm, maxTerm);

        return termRange;
    }

    private TimeRange createTimeRange(final Date date) {
        final String minTime = parameterService.getParameterAsString(REJECT_TIME_FROM);
        final String maxTime = parameterService.getParameterAsString(REJECT_TIME_TO);
        final TimeRange timeRange = new TimeRange(date, minTime, maxTime);

        return timeRange;
    }
}
