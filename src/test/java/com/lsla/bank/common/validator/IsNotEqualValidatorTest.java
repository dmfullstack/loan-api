package com.lsla.bank.common.validator;

import com.lsla.bank.error.ErrorRepresentation;
import com.lsla.bank.common.wrapper.amount.Amount;
import com.lsla.bank.common.wrapper.term.Term;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
@SpringBootTest
public class IsNotEqualValidatorTest {

    private final static ValidatorError ok = ValidatorError.ok();
    private final static ValidatorError error = new ValidatorError(ErrorRepresentation.IS_NOT_EQUALS_VALIDATOR_ERROR);

    private String message;
    private Object first;
    private Object second;
    private ValidatorError expected;

    public IsNotEqualValidatorTest(final String message, final Object first, final Object second, final ValidatorError expected) {
        this.message = message;
        this.first = first;
        this.second = second;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Two amount equal", new Amount(200), new Amount(200), error},
                {"Two amount not equal", new Amount(100), new Amount(200), ok},
                {"Two term equal", new Term(3), new Term(3), error},
                {"Two term not equal", new Term(3), new Term(6), ok}
        });
    }

    @Test
    public void test() {
        //given
        Validator validator = new IsNotEqualValidator(first, second);
        //when
        ValidatorError validatorError = validator.isValid();
        //then
        Assert.assertEquals(message, expected, validatorError);
    }

}
