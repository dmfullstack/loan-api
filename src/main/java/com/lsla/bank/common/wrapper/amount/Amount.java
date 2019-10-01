package com.lsla.bank.common.wrapper.amount;

import java.math.BigDecimal;

/**
 * Wrapper class for Amount
 */
public class Amount {
    private BigDecimal value;

    public Amount(final int value) {
        this.value = new BigDecimal(value);
    }

    public Amount(final BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }

    public boolean isGreater(final Amount compare) {
        return value.compareTo(compare.getValue()) == 1;
    }

    public boolean isLess(final Amount compare) {
        return value.compareTo(compare.getValue()) == -1;
    }

    public boolean isEquals(final Amount compare) {
        return value.compareTo(compare.getValue()) == 0;
    }

    public Amount add(final Amount amount) {
        return new Amount(value.add(amount.getValue()));
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Amount && isEquals((Amount) obj));
    }
}
