package com.lsla.bank.common.wrapper.range;

import com.lsla.bank.common.wrapper.amount.Amount;

public class AmountRange implements Range {
    private Amount actual;
    private Amount min;
    private Amount max;

    public AmountRange(final Amount actual, final Amount min, final Amount max) {
        this.actual = actual;
        this.min = min;
        this.max = max;
    }

    public Amount getMin() {
        return min;
    }

    public Amount getMax() {
        return max;
    }

    @Override
    public boolean isInRange() {
        return (actual.isGreater(min) || actual.isEquals(min)) && (actual.isLess(max) || actual.isEquals(max));
    }

    @Override
    public String toString() {
        return "AmountRange [actual=" + actual + ", min=" + min + ", max=" + max + "]";
    }
}
