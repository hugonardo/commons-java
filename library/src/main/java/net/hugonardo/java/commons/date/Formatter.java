package net.hugonardo.java.commons.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Formatter {

    public static String format(Calendar calendar, String pattern, Locale locale) {
        DateFormat formatter = new SimpleDateFormat(pattern, locale);
        formatter.setTimeZone(calendar.getTimeZone());
        return formatter.format(calendar.getTime());
    }
}
