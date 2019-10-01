package com.lsla.bank.loan.component;

import static com.lsla.bank.parameter.ParameterName.*;
import static com.lsla.bank.util.DateCreator.createDate;

import com.lsla.bank.common.db.Entity;
import com.lsla.bank.error.db.ErrorEntity;
import com.lsla.bank.error.service.ErrorService;
import com.lsla.bank.loan.db.LoanDAO;
import com.lsla.bank.loan.db.LoanEntity;
import com.lsla.bank.loan.dto.*;
import com.lsla.bank.util.DateCreator;
import com.lsla.bank.parameter.ParameterService;
import com.lsla.bank.common.wrapper.amount.Amount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplyForLoanComponentTest {

    @Autowired
    private ApplyForLoanComponent service;

    @MockBean
    private ParameterService parameterService;

    @MockBean
    private LoanDAO loanDAO;

    @MockBean
    private ErrorService errorService;

    @Mock
    private LoanEntity loanEntity;

    @Mock
    private ErrorEntity errorEntity;

    private final int MIN_AMOUNT = 200;
    private final int MAX_AMOUNT = 1000;
    private final int MIN_TERM = 5;
    private final int MAX_TERM = 10;
    private final String MAX_HOUR = "06:00";
    private final String MIN_HOUR = "00:00";
    private final Long expectedId = 1L;

    @Before
    public void setup() {
        Mockito.when(parameterService.getParameterAsInt(AMOUNT_MIN)).thenReturn(MIN_AMOUNT);
        Mockito.when(parameterService.getParameterAsInt(AMOUNT_MAX)).thenReturn(MAX_AMOUNT);

        Mockito.when(parameterService.getParameterAsInt(TERM_DAY_MIN)).thenReturn(MIN_TERM);
        Mockito.when(parameterService.getParameterAsInt(TERM_DAY_MAX)).thenReturn(MAX_TERM);

        Mockito.when(parameterService.getParameterAsString(REJECT_TIME_FROM)).thenReturn(MIN_HOUR);
        Mockito.when(parameterService.getParameterAsString(REJECT_TIME_TO)).thenReturn(MAX_HOUR);

        Mockito.when(loanEntity.getId()).thenReturn(expectedId);
        Mockito.when(loanEntity.getDueDate()).thenReturn(createDate(1,1));
        Mockito.when(loanEntity.getTotalAmount()).thenReturn(new Amount(1));
        Mockito.when(loanDAO.save(Mockito.any())).thenReturn(loanEntity);

        Mockito.when(errorService.getEntity(Mockito.any())).thenReturn(errorEntity);
    }

    @Test
    public void notInAmountRange()  {
        //given

        //when
        ErrorEntity entity = (ErrorEntity) service.applyForLoan(new LoanRequestDTO(1, 3), createDate(0,0));
        //then
        Assert.assertEquals(errorEntity, entity);

    }

    @Test
    public void notInTermRange() {
        //given

        //when
        ErrorEntity entity = (ErrorEntity) service.applyForLoan(new LoanRequestDTO(300, 3), createDate(0,0));
        //then
        Assert.assertEquals(errorEntity, entity);

    }

    @Test
    public void correctTimeAndMaxAmount() {
        //given

        //when
        LoanEntity entity = (LoanEntity) service.applyForLoan( new LoanRequestDTO(1000, 6), createDate(10,0));
        //then
        Assert.assertEquals(expectedId, entity.getId());
    }

    @Test
    public void correctTimeAndNotMaxAmount() {
        //given

        //when
        LoanEntity entity = (LoanEntity) service.applyForLoan(new LoanRequestDTO(900, 6), createDate(10,0));
        //then
        Assert.assertEquals(expectedId, entity.getId());
    }

    @Test
    public void timeInRangeAndMaxAmount() {
        //given

        //when
        Entity entity = service.applyForLoan(new LoanRequestDTO(1000, 3), DateCreator.createDate(0,0));
        //then
        Assert.assertTrue(entity instanceof ErrorEntity);
    }

    @Test
    public void timeInRangeAndNotMaxAmount() {
        //given

        //when
        LoanEntity entity = (LoanEntity) service.applyForLoan(new LoanRequestDTO(900, 10), createDate(0,0));
        //then
        Assert.assertEquals(expectedId, entity.getId());
    }

    @Test
    public void nullAmount() {
        //given

        //when
        LoanEntity entity = (LoanEntity) service.applyForLoan(new LoanRequestDTO(900, 10), createDate(0,0));
        //then
        Assert.assertEquals(expectedId, entity.getId());
    }

    @Test
    public void nullTerm() {
        //given

        //when
        LoanEntity entity = (LoanEntity) service.applyForLoan(new LoanRequestDTO(900, 10), createDate(0,0));
        //then
        Assert.assertEquals(expectedId, entity.getId());
    }
}
