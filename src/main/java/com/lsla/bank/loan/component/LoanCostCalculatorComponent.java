package com.lsla.bank.loan.component;

import com.lsla.bank.common.wrapper.amount.Percent;
import com.lsla.bank.common.wrapper.amount.Amount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoanCostCalculatorComponent {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    public Amount percentFromAmount(final Amount amount, final int percent) {
        LOGGER.debug("Calculating {}% from amount={}", percent, amount);
        final Percent p = new Percent(percent);
        final Amount totalAmount = amount.add(p.calculate(amount));

        LOGGER.debug("Total cost={}", totalAmount);
        return totalAmount;
    }

}
