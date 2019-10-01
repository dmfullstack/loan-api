package com.lsla.bank.common.validator.range;

import com.lsla.bank.common.validator.Validator;
import com.lsla.bank.common.validator.ValidatorError;
import com.lsla.bank.error.ErrorRepresentation;
import com.lsla.bank.common.wrapper.range.Range;
import com.lsla.bank.common.wrapper.range.TimeRange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;

import static com.lsla.bank.util.DateCreator.createDate;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@SpringBootTest
public class NotInRangeValidatorTest {
    private static final ValidatorError ok = ValidatorError.ok();
    private static final ValidatorError error = new ValidatorError(ErrorRepresentation.TIME_VALIDATOR_ERROR);

    private String message;
    private Range range;
    private ValidatorError expectedValidationError;

    private static final String MIN_TIME = "00:00";
    private static final String MAX_TIME = "06:00";

    public NotInRangeValidatorTest(final String message, final Range range, final ValidatorError expectedValidationError) {
        this.message = message;
        this.range = range;
        this.expectedValidationError = expectedValidationError;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Date 00:00 failed.", createTimeRange(0,0), error},
                {"Date 00:01 failed.", createTimeRange(0, 1), error},
                {"Date 01:20 failed.", createTimeRange(1, 20), error},
                {"Date 05:59 failed.", createTimeRange(5,59), error},
                {"Date 06:00 failed.", createTimeRange(6,0), error},
                {"Date 06:01 failed.", createTimeRange(6,1), ok},
                {"Date 07:20 failed.", createTimeRange(7,20), ok},
                {"Date 12:00 failed.", createTimeRange(12,00), ok},
                {"Date 23:15 failed.", createTimeRange(23,15), ok},
                {"Date 23:59 failed.", createTimeRange(23,59), ok}
        });
    }

    @Test
    public void testAll(){
        //given
        final Validator validator = new NotInRangeValidator(range, ErrorRepresentation.TIME_VALIDATOR_ERROR);
        //when
        final ValidatorError validationResult = validator.isValid();
        //then
        assertEquals(message, expectedValidationError, validationResult);
    }

    private static TimeRange createTimeRange(final int hour, final int minute) {
        return new TimeRange(createDate(hour, minute), MIN_TIME, MAX_TIME);
    }
}
