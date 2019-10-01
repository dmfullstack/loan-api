package com.lsla.bank.loan.db;

import com.lsla.bank.common.db.Entity;
import com.lsla.bank.common.wrapper.amount.Amount;

import java.util.Date;

public class LoanEntity implements Entity {
    private Long id;
    private Date dueDate;
    private Amount totalAmount;

    public LoanEntity(final Long id, final Date dueDate, final Amount totalAmount) {
        this.id = id;
        this.dueDate = dueDate;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Amount getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "LoanEntity [id=" + id + ", dueDate=" + dueDate + ", totalAmount=" + totalAmount + "]";
    }
}
