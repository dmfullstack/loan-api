package com.lsla.bank.loan.controller;

import com.lsla.bank.common.db.Entity;
import com.lsla.bank.error.db.ErrorEntity;
import com.lsla.bank.error.dto.ErrorDTO;
import com.lsla.bank.error.dto.ErrorMapper;
import com.lsla.bank.loan.db.LoanEntity;
import com.lsla.bank.loan.dto.LoanMapper;
import com.lsla.bank.loan.dto.LoanRequestDTO;
import com.lsla.bank.common.dto.Message;
import com.lsla.bank.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;

/**
 * Default implementation of LoanController
 */
@RestController("api/v1/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private ErrorMapper errorMapper;

    @Autowired
    private LoanMapper loanMapper;

    @PostMapping
    public ResponseEntity<Message> applyForLoan(@RequestBody final LoanRequestDTO requestDTO, final UriComponentsBuilder uriComponentsBuilder) {
        try {
            Entity entity = loanService.applyForLoan(requestDTO, new Date());
            if (entity instanceof LoanEntity) {
                UriComponents uriComponents = uriComponentsBuilder.path("api/v1/loans/{id}").buildAndExpand(entity.getId());
                return ResponseEntity.created(uriComponents.toUri()).build();
            } else {
                ErrorDTO errorDTO = errorMapper.convertToDTO((ErrorEntity) entity);
                return new ResponseEntity<Message>(errorDTO, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Not in requirements, for test purpose only.
     * @param id id of loan
     * @return LoanEntity
     */

    @GetMapping("/api/v1/loans/{id}")
    public ResponseEntity<Message> getLoan(@PathVariable Long id) {

        LoanEntity entity = (LoanEntity) loanService.getLoan(id);
        if (entity != null) {
            return ResponseEntity.ok(loanMapper.convertToDTO(entity));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/api/v1/loans/{id}")
    public ResponseEntity<Message> extendLoan(@PathVariable Long id) {
        LoanEntity entity = (LoanEntity) loanService.extendLoan(id);
        if (entity != null) {
            return ResponseEntity.ok(loanMapper.convertToDTO(entity));
        }
        return ResponseEntity.notFound().build();
    }
}
