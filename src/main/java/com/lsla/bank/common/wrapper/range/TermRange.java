package com.lsla.bank.common.wrapper.range;

import com.lsla.bank.common.wrapper.term.Term;

public class TermRange implements Range {
    private Term actual;
    private Term min;
    private Term max;

    public TermRange(final Term actual, final Term min, final Term max) {
        this.actual = actual;
        this.min = min;
        this.max = max;
    }

    public Term getMin() {
        return min;
    }

    public Term getMax() {
        return max;
    }

    @Override
    public boolean isInRange() {
        return actual.getValue() >= min.getValue() && actual.getValue() <= max.getValue();
    }

    @Override
    public String toString() {
        return "TermRange [actual=" + actual.getValue() + ", min=" + min.getValue() + ", max=" + getMax().getValue() + "]";
    }
}
