package com.lsla.bank.loan.dto;

import com.lsla.bank.common.dto.Message;
import com.lsla.bank.common.wrapper.amount.Amount;

import java.math.BigDecimal;
import java.util.Date;

public class LoanResponseDTO implements Message {
    private Long id;
    private BigDecimal totalAmount;
    private Date dueDate;

    public LoanResponseDTO(final Long id, final Amount totalAmount, final Date dueDate) {
        this.id = id;
        this.totalAmount = totalAmount.getValue();
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }
}
