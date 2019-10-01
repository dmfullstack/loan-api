package com.lsla.bank.common.validator.range;

import com.lsla.bank.common.validator.Validator;
import com.lsla.bank.common.validator.ValidatorError;
import com.lsla.bank.error.ErrorRepresentation;
import com.lsla.bank.common.wrapper.range.Range;
import com.lsla.bank.common.wrapper.amount.Amount;
import com.lsla.bank.common.wrapper.range.AmountRange;
import com.lsla.bank.common.wrapper.term.Term;
import com.lsla.bank.common.wrapper.range.TermRange;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
@SpringBootTest
public class InRangeValidatorTest {
    private static final ValidatorError ok = ValidatorError.ok();
    private static final ValidatorError error = new ValidatorError(ErrorRepresentation.AMOUNT_VALIDATOR_ERROR);

    private static final int MIN_AMOUNT = 1000;
    private static final int MAX_AMOUNT = 10000;
    private static final int MIN_TERM = 5;
    private static final int MAX_TERM = 20;

    private String message;
    private Range range;
    private ValidatorError expectedValidationError;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Amount Less then minimum failed.", createAmountRange(MIN_AMOUNT - 1), error},
                {"Amount Equals minimum failed.", createAmountRange(MIN_AMOUNT), ok},
                {"Amount Between minimum and maximum failed.", createAmountRange(MAX_AMOUNT - MIN_AMOUNT), ok},
                {"Amount Equals then maximum failed.", createAmountRange(MAX_AMOUNT), ok},
                {"Amount More then maximum failed.", createAmountRange(MAX_AMOUNT + 10), error},
                {"Term Less then minimum", createTermRange(MIN_TERM-1), error},
                {"Term Between minimum and maximum failed.", createAmountRange(MIN_AMOUNT + 1), ok},
        });
    }

    public InRangeValidatorTest(final String message, final Range range, final ValidatorError expectedValidationError) {
        this.message = message;
        this.range = range;
        this.expectedValidationError = expectedValidationError;
    }

    @Test
    public void testAll() {
        //given
        final Validator validator = new InRangeValidator(range, ErrorRepresentation.AMOUNT_VALIDATOR_ERROR);
        //when
        final ValidatorError validatorError = validator.isValid();
        //then
        Assert.assertEquals(message, expectedValidationError, validatorError);
    }

    private static AmountRange createAmountRange(int actual) {
        return new AmountRange(new Amount(actual), new Amount(MIN_AMOUNT), new Amount(MAX_AMOUNT));
    }

    private static TermRange createTermRange(int term) {
        return new TermRange(new Term(term), new Term(MIN_TERM), new Term(MAX_TERM));
    }
}
