package com.lsla.bank.common.wrapper.amount;

import java.math.BigDecimal;

/**
 * Wrapper class for percent
 */
public class Percent {
    private int value;
    private int ONE_HUNDRED = 100;
    public Percent(final int percent) {
        this.value = percent;
    }

    public int getValue() {
        return value;
    }

    public Amount calculate(final Amount amount) {
        return new Amount(amount.getValue().multiply(new BigDecimal(value)).divide(new BigDecimal(ONE_HUNDRED)));
    }

    @Override
    public String toString() {
        return "Percent: [value=" + value + "]";
    }
}
