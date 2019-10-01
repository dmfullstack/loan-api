package com.lsla.bank.loan.dto;

import com.lsla.bank.loan.db.LoanEntity;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    public LoanResponseDTO convertToDTO(final LoanEntity entity) {
        return new LoanResponseDTO(entity.getId(), entity.getTotalAmount(), entity.getDueDate());
    }
}
