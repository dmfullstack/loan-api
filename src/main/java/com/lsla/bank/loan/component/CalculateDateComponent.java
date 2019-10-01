package com.lsla.bank.loan.component;

import com.lsla.bank.common.wrapper.term.Term;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class CalculateDateComponent {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    public Date addDays(final Term term) {
        return addDays(term, new Date());
    }

    public Date addDays(final Term term, final Date date) {
        LOGGER.debug("Calculating date for term={} and date={}", term, date);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, term.getValue());
        final Date calculatedDate = calendar.getTime();

        LOGGER.debug("Calculated date={}", calculatedDate);
        return calculatedDate;
    }
}
