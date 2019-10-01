package com.lsla.bank.common.wrapper.term;

public class Term {
    private int value;
    private TermType termType;

    public Term(final int value) {
        this.value = value;
        this.termType = TermType.DAY;
    }

    public int getValue() {
        return value;
    }

    public TermType getTermType() {
        return termType;
    }

    @Override
    public String toString() {
        return "Term [value=" + value + ", type=" + termType + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Term && ((Term) obj).value == value && ((Term) obj).termType.equals(termType));
    }
}
