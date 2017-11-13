package net.hugonardo.java.commons.date;

import java.util.Calendar;
import java.util.Date;

public class SystemClock implements Clock {

    private static final SystemClock sInstance = new SystemClock();

    public static Clock instance() {
        return sInstance;
    }

    @Override
    public Calendar getCalendar() {
        return Calendar.getInstance();
    }

    @Override
    public long getCurrentTimeInMillis() {
        return System.currentTimeMillis();
    }

    @Override
    public Date getDate() {
        return new Date();
    }
}
