package com.lsla.bank.loan.component;

import com.lsla.bank.util.DateCreator;
import com.lsla.bank.common.wrapper.term.Term;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculateDateComponentTest {

    @Autowired
    private CalculateDateComponent calculateDateComponent;

    @Test
    public void addInSameMonth() {
        //given
        final Term term = new Term(3);
        final Date dateToCheck = DateCreator.createDate(2019, 0 ,1);
        //when
        final Date calculatedDate = calculateDateComponent.addDays(term, dateToCheck);
        //then
        Assert.assertEquals(DateCreator.createDate(2019, 0, 4), calculatedDate);
    }

    @Test
    public void addToNextMonth() {
        //given
        final Term term = new Term(3);
        final Date dateToCheck = DateCreator.createDate(2019, 0,31);
        //when
        final Date calculatedDate = calculateDateComponent.addDays(term, dateToCheck);
        //then
        Assert.assertEquals(DateCreator.createDate(2019, 1, 3), calculatedDate);
    }

    @Test
    public void addToNextYear() {
        //given
        final Term term = new Term(3);
        final Date dateToCheck = DateCreator.createDate(2018, 11,31);
        //when
        final Date calculatedDate = calculateDateComponent.addDays(term, dateToCheck);
        //then
        Assert.assertEquals(DateCreator.createDate(2019, 0, 3), calculatedDate);
    }
}