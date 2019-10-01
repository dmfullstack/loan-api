package com.lsla.bank.loan.dto;

import com.lsla.bank.common.dto.Message;

public class LoanRequestDTO implements Message {
    private int amount;
    private int term;

    public LoanRequestDTO(int amount, int term) {
        this.amount = amount;
        this.term = term;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "LoanRequestDTO[" +
                "amount=" + amount +
                ", term=" + term +
                ']';
    }
}
