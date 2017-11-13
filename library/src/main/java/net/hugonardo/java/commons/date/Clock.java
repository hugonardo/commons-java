package net.hugonardo.java.commons.date;

import java.util.Calendar;
import java.util.Date;

public interface Clock {

    Calendar getCalendar();

    long getCurrentTimeInMillis();

    Date getDate();
}
