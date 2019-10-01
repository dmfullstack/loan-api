package com.lsla.bank.loan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsla.bank.common.wrapper.amount.Amount;
import com.lsla.bank.error.db.ErrorEntity;
import com.lsla.bank.loan.db.LoanEntity;
import com.lsla.bank.loan.dto.LoanRequestDTO;
import com.lsla.bank.loan.service.LoanService;
import com.lsla.bank.util.DateCreator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LoanControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @Test
    public void applyForLoanCorrect() throws Exception {
        Mockito.when(loanService.applyForLoan(Mockito.any(), Mockito.any())).thenReturn(new LoanEntity(0L, new Date(), new Amount(1)));

        mockMvc.perform(post("/api/v1/loans")
                .content(new ObjectMapper().writeValueAsString(new LoanRequestDTO(2000, 5)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void applyForLoanError() throws Exception {
        Mockito.when(loanService.applyForLoan(Mockito.any(), Mockito.any())).thenReturn(new ErrorEntity(1L, 1, "error"));

        mockMvc.perform(post("/api/v1/loans")
                .content(new ObjectMapper().writeValueAsString(new LoanRequestDTO(2000, 5)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void getCorrect() throws Exception {
        Mockito.when(loanService.getLoan(Mockito.any())).thenReturn(new LoanEntity(1L, new Date(), new Amount(10011)));
        mockMvc.perform( get("/api/v1/loans/1")).andExpect(content().string(containsString("10011")));
    }

    @Test
    public void extendLoan() throws Exception {
        Mockito.when(loanService.extendLoan(Mockito.any())).thenReturn(new LoanEntity(1L, DateCreator.createDate(2019, 0,1), new Amount(10011)));
        mockMvc.perform( patch("/api/v1/loans/1")).andExpect(content().string(containsString("2019-01-01")));
    }

    @Test
    public void extendLoanError() throws Exception {
        Mockito.when(loanService.extendLoan(Mockito.any())).thenReturn(null);
        mockMvc.perform( patch("/api/v1/loans/1")).andExpect(status().isNotFound());
    }
}