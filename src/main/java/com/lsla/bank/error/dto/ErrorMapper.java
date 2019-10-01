package com.lsla.bank.error.dto;

import com.lsla.bank.error.db.ErrorEntity;
import org.springframework.stereotype.Component;

@Component
public class ErrorMapper {
    public ErrorDTO convertToDTO(final ErrorEntity errorEntity) {
        return new ErrorDTO(errorEntity.getCode(), errorEntity.getDescription());
    }
}
