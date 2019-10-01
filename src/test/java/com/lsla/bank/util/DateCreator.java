package com.lsla.bank.util;

import java.util.Calendar;
import java.util.Date;

public class DateCreator {
    //for static use only
    private DateCreator() {
    }

    public static Date createDate(final int hour, final int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 1, 1, hour, minute, 0);
        return cal.getTime();
    }

    public static Date createDate(final int year, final int month, final int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day);
        return cal.getTime();
    }

}
