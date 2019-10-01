package com.lsla.bank.common.validator;

import static com.lsla.bank.util.DateCreator.createDate;

import com.lsla.bank.common.validator.range.NotInRangeValidator;
import com.lsla.bank.error.ErrorRepresentation;
import com.lsla.bank.common.wrapper.range.TimeRange;
import com.lsla.bank.common.wrapper.amount.Amount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NandValidatorTest {

    private ValidatorError error = new ValidatorError(200, "Time range and max amount");
    @Test
    public void errorAndError() {
        Validator validator = new NandValidator(new NotInRangeValidator(new TimeRange(createDate(0,0),"00:00", "06:00"), ErrorRepresentation.TIME_VALIDATOR_ERROR), new IsNotEqualValidator(new Amount(3), new Amount(3)), error);
        ValidatorError validatorError = validator.isValid();
        Assert.assertEquals(error, validatorError);
    }

    @Test
    public void correctAndError() {
        Validator validator = new NandValidator(new NotInRangeValidator(new TimeRange(createDate(10,0),"00:00", "06:00"), ErrorRepresentation.TIME_VALIDATOR_ERROR), new IsNotEqualValidator(new Amount(3), new Amount(3)), error);
        ValidatorError validatorError = validator.isValid();
        Assert.assertTrue(validatorError.equals(ValidatorError.ok()));
    }

    @Test
    public void errorAndCorrect() {
        Validator validator = new NandValidator(new NotInRangeValidator(new TimeRange(createDate(0,0), "00:00", "06:00"), ErrorRepresentation.TIME_VALIDATOR_ERROR), new IsNotEqualValidator(new Amount(3), new Amount(4)), error);
        ValidatorError validatorError = validator.isValid();
        Assert.assertTrue(validatorError.equals(ValidatorError.ok()));
    }

    @Test
    public void correctAndCorrect() {
        Validator validator = new NandValidator(new NotInRangeValidator(new TimeRange(createDate(10,0),"00:00", "06:00"), ErrorRepresentation.TIME_VALIDATOR_ERROR), new IsNotEqualValidator(new Amount(3), new Amount(4)), error);
        ValidatorError validatorError = validator.isValid();
        Assert.assertTrue(validatorError.equals(ValidatorError.ok()));
    }
}
