package com.walterwhites.library.business.utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A collections of date-oriented methods surrounding the use of the {@link java.util.Calendar} and
 * {@link java.util.Date} classes.
 *
 * @author Alex Arana
 */
public final class DateUtils {

    public static String formatDayMonthYear(GregorianCalendar calendar){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MMM-dd");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }


    /**
     * Converts an instance of {@link java.util.Date} object to {@link java.util.Calendar}.
     *
     * @param date
     *            date object to convert.
     * @return Converted <code>Calendar</code> object.
     */
    public static Calendar toCalendar(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Creates an instance of {@link java.util.Calendar} from the given time in milliseconds.
     * <p>
     * The input milliseconds value represents the specified number of milliseconds since the standard base time known
     * as "the epoch", namely January 1, 1970, 00:00:00 GMT.
     *
     * @param millis
     *            A given time corresponding to the number of milliseconds since January 1, 1970, 00:00:00 GMT
     * @return Converted <code>Calendar</code> object.
     */
    public static Calendar toCalendar(final long millis) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar;
    }

    /**
     * Converts a given time as an instance of {@link java.util.Date} into a {@link XMLGregorianCalendar} object.
     *
     * @param date
     *            A given time as an instance of <code>java.util.Date</code>
     * @return A new instance of <code>XMLGregorianCalendar</code> representing the input time
     */
    public static XMLGregorianCalendar toXmlGregorianCalendar(final Date date) {
        return toXmlGregorianCalendar(date.getTime());
    }

    /**
     * Converts a given time in milliseconds into a {@link XMLGregorianCalendar} object.
     * <p>
     * The input milliseconds value represents the specified number of milliseconds since the standard base time known
     * as "the epoch", namely January 1, 1970, 00:00:00 GMT.
     *
     * @param date
     *            A given time corresponding to the number of milliseconds since January 1, 1970, 00:00:00 GMT
     * @return A new instance of <code>XMLGregorianCalendar</code> representing the input time
     */
    public static XMLGregorianCalendar toXmlGregorianCalendar(final long date) {
        try {
            final GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    calendar);
        }
        catch (final DatatypeConfigurationException ex) {
            System.out.println("Unable to convert date '%s' to an XMLGregorianCalendar object");
            return null;
        }
    }
}
