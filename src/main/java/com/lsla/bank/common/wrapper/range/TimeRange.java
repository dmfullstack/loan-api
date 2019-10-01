package com.lsla.bank.common.wrapper.range;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeRange implements Range{
    private Date actual;
    private String min;
    private String max;
    private final String DATE_PATTERN = "HH:mm";
    final SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

    public TimeRange(final Date actual, final String min, final String max) {
        this.actual = actual;
        this.min = min;
        this.max = max;
    }

    public boolean isNotInRange() {
        return !isInRange();
    }

    public boolean isInRange() {
        try {
            final Date from = sdf.parse(min);
            final Date to = sdf.parse(max);
            final Date applicationDate = sdf.parse(sdf.format(actual));
            return isAfterOrEquals(applicationDate, from) && isBeforeOrEquals(applicationDate, to);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isAfterOrEquals(final Date date, final Date toCompare) {
        return date.equals(toCompare) || date.after(toCompare);
    }

    private boolean isBeforeOrEquals(final Date date, final Date toCompare) {
        return date.equals(toCompare) || date.before(toCompare);
    }

    @Override
    public String toString() {
        return "TimeRange [min=" + min + ", max=" + max + "]";
    }
}
