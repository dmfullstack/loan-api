package com.lsla.bank.loan.component;

import com.lsla.bank.common.wrapper.amount.Amount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanCostCalculatorComponentTest {

    @Autowired
    private LoanCostCalculatorComponent calculatorComponent;

    @Test
    public void calculateTotalCostFor10PercentAndAmoun100() {
        //given
        final Amount amount = new Amount(100);
        final int percent = 10;
        //when
        final Amount totalAmount = calculatorComponent.percentFromAmount(amount, percent);
        //then
        Assert.assertEquals(new Amount(110), totalAmount);
    }

    @Test
    public void calculateTotalCostFor25PercentAndAmount1000() {
        //given
        final Amount amount = new Amount(1000);
        final int percent = 25;
        //when
        final Amount totalAmount = calculatorComponent.percentFromAmount(amount, percent);
        //then
        Assert.assertEquals(new Amount(1250), totalAmount);
    }

    @Test
    public void calculateTotalCostFor55PercentAndAmount10() {
        //given
        final Amount amount = new Amount(10);
        final int percent = 55;
        //when
        final Amount totalAmount = calculatorComponent.percentFromAmount(amount, percent);
        //then
        Assert.assertEquals(new Amount(new BigDecimal(15.5)), totalAmount);
    }
}