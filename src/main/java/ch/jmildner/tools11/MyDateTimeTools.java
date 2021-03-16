package ch.jmildner.tools11;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * The final class <code>DateTime</code> has some useful
 * <code>static</code> methods for database programming.
 *
 * @author Johann Mildner, Basel
 */
public final class MyDateTimeTools {

    public static void main(String[] args) throws Exception {

        System.out.println(MyDateTimeTools.getCurrentDate());
        System.out.println(MyDateTimeTools.getCurrentTime());
        System.out.println(MyDateTimeTools.makeRandomTimestamp());
        System.out.println(MyDateTimeTools.makeRandomTimestamp(2122));
        System.out.println(MyDateTimeTools.makeRandomTimestamp(1999, 2000));
        System.out.println(MyDateTimeTools.makeTimestamp(1111, 11, 11));
        System.out.println(MyDateTimeTools.makeTimestamp(1111, 11, 11, 11, 22, 33));
        System.out.println(MyDateTimeTools.makeTimestamp(1111, 11, 11, 11, 22, 33, 1234));
        System.out.println(MyDateTimeTools.getCurrentTimestamp(false));
        System.out.println(MyDateTimeTools.getCurrentTime());
        System.out.println(MyDateTimeTools.makeRandomDateOfBirth(55));
        System.out.println(MyDateTimeTools.getRandomAge(44));
        System.out.println(MyDateTimeTools.makeDate(1111, 11, 11));
        System.out.println(MyDateTimeTools.makeTime(11, 11, 11));
        System.out.println(MyDateTimeTools.makeRandomDate(2111));
        System.out.println(MyDateTimeTools.makeRandomDate(2000, 2005));
        System.out.println(MyDateTimeTools.makeRandomDate());
        System.out.println(MyDateTimeTools.makeRandomTime());
        System.out.println(MyDateTimeTools.makeDate(2011, 12, 11, 11, 11, 11));
    }

    /**
     * The constructor is private so that
     * <code>MyDateTimeTools</code> can't be be instantiated.
     */
    private MyDateTimeTools() {
    }

    /**
     * Returns the current date.
     *
     * @return theCurrentDate
     */
    public static Date getCurrentDate() {
        Calendar cal = GregorianCalendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH); // zero based
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return new Date(new GregorianCalendar(year, month, day, 0, 0, 0).getTimeInMillis());
    }

    /**
     * Returns the current time.
     *
     * @return theCurrentTime (1970/01/01 + current time)
     */
    static public Time getCurrentTime() {
        int anHour = 1000 * 60 * 60; // summertime ???
        return new Time(GregorianCalendar.getInstance().getTimeInMillis()
                - getCurrentDate().getTime() - anHour);
    }

    /**
     * Returns the current timestamp.
     *
     * @param equalsAllowed if allowed - true, if not allowed false or omitted.
     * @return theCurrentTimestamp
     */
    public static Timestamp getCurrentTimestamp(boolean... equalsAllowed) {
        if (equalsAllowed.length == 1 && !equalsAllowed[0]) {
            /*
             * waits a millisecond so that there are
             * no equal timestamps
             */
            MyTools.sleep(1);
        }
        return new Timestamp(GregorianCalendar.getInstance().getTimeInMillis());
    }

    /**
     * Returns a random age.
     * The method uses the Gaussian bell curve.
     *
     * @param peak the peak of the Gaussian bell curve
     * @return aRandomAge
     */
    static public int getRandomAge(int peak) {
        Random r = new Random();
        int mult = peak / 5;
        int min = (int) (peak * 0.75);
        int max = (int) (peak * 1.25);

        while (true) {
            double age = (r.nextGaussian() * mult);

            int ageRounded = peak + Math.round((float) age);

            if (ageRounded >= min && ageRounded <= max) {
                return ageRounded;
            }
        }
    }

    /**
     * Returns the year of the birth to a current age.
     *
     * @param age the age to identify the year
     * @return year of birth
     */
    static public int getYearOfBirth(int age) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return year - age;
    }

    /**
     * @param peak age on the peak of the Gaussian bell curve
     * @return das date of birth
     * @throws Exception invalid Date(shouldn't occur)
     */
    static public Date makeRandomDateOfBirth(int peak) throws Exception {
        return makeRandomDate(getYearOfBirth(getRandomAge(peak)));
    }

    /**
     * Returns the date (year, month, day, hr, min, sec).
     *
     * @param year  the Year
     * @param month the Month
     * @param day   the Day
     * @param hr    the Hour
     * @param min   the Minute
     * @param sec   the Second
     * @return Date
     * @throws Exception illegal Date/Time
     */
    static public Date makeDate(
            int year, int month, int day, int hr, int min, int sec) throws Exception {
        checkDate(year, month, day, hr, min, sec, 0);
        return new Date(
                makeGregorianCalendar(year, month, day, hr, min, sec).getTimeInMillis());
    }

    /**
     * Returns the date (year, month, day).
     *
     * @param year  the year
     * @param month the month
     * @param day   the day
     * @return aDate the Date
     * @throws Exception illegal Date/Time
     */
    public static Date makeDate(
            int year, int month, int day) throws Exception {
        return makeDate(year, month, day, 0, 0, 0);
    }

    /**
     * Returns a random date between fromYear and toYear.
     *
     * @param yearFrom the year from
     * @param yearTo   the year to
     * @return Date a Date between from and to
     * @throws Exception illegal Date/Time
     */
    static public Date makeRandomDate(
            int yearFrom, int yearTo) throws Exception {
        int year = getRandom(yearFrom, yearTo);
        int month = getRandom(1, 12);
        int day = getRandom(1, lastDayOfMonth(year, month));
        return makeDate(year, month, day);
    }

    /**
     * Returns a random date between 1950 and 2020.
     *
     * @return a date
     * @throws Exception illegal Date/Time
     */
    static public Date makeRandomDate() throws Exception {
        return makeRandomDate(1950, 2010);
    }

    /**
     * Returns a random date in a given year.
     *
     * @param year the year
     * @return a random date in that year
     * @throws Exception illegal Date/Time
     */
    static public Date makeRandomDate(int year) throws Exception {
        return makeRandomDate(year, year);
    }

    /**
     * returns the time (hr, min, sec).
     *
     * @param hr  the hour
     * @param min the minute
     * @param sec the second
     * @return aTime (1970/01/01 hr:min:sec)
     * @throws Exception illegal Date/Time
     */
    static public Time makeTime(
            int hr, int min, int sec) throws Exception {
        checkDate(1970, 1, 1, hr, min, sec, 0);
        return new Time(
                makeGregorianCalendar(1970, 1, 1, hr, min, sec).
                        getTimeInMillis());
    }

    /**
     * returns a random time.
     *
     * @return a time
     * @throws Exception illegal Date/Time
     */
    static public Time makeRandomTime() throws Exception {
        return new Time(makeGregorianCalendar(
                1970, 1, 1,
                getRandom(0, 23),
                getRandom(0, 58),
                getRandom(0, 59)).getTimeInMillis());
    }

    /**
     * Returns the Timestamp for year, month, day, hr, min, sec.
     *
     * @param year  the year
     * @param month the month
     * @param day   the day
     * @param hr    the hour
     * @param min   the minute
     * @param sec   the second
     * @return theTimestamp
     * @throws Exception illegal Date/Time
     */
    static public Timestamp makeTimestamp(
            int year, int month, int day,
            int hr, int min, int sec) throws Exception {
        return makeTimestamp(year, month, day, hr, min, sec, 0);
    }

    /**
     * Returns the timestamp for year, month, day.
     *
     * @param year  the year
     * @param month the month
     * @param day   the day
     * @return theTimestamp
     * @throws Exception illegal Date/Time
     */
    static public Timestamp makeTimestamp(
            int year, int month, int day) throws Exception {
        return makeTimestamp(year, month, day, 0, 0, 0, 0);
    }

    /**
     * Returns the timestamp for year, month, day, hr, min, sec, microSec.
     *
     * @param year       the year
     * @param month      the month
     * @param day        the day
     * @param hr         the hour
     * @param min        the minute
     * @param sec        the second
     * @param nanoSecond the nanoSecond
     * @return theTimestamp
     * @throws Exception illegal Date/Time
     */
    static public Timestamp makeTimestamp(
            int year, int month, int day,
            int hr, int min, int sec, int nanoSecond) throws Exception {
        checkDate(year, month, day, hr, min, sec, nanoSecond);

        Timestamp ts = new Timestamp(
                makeGregorianCalendar(year, month, day, hr, min, sec).
                        getTimeInMillis());
        ts.setNanos(nanoSecond);

        return ts;
    }

    /**
     * returns a random timestamp.
     *
     * @return aTimestamp
     */
    static public Timestamp makeRandomTimestamp() {
        return new Timestamp(MyTools.getRandom(1L, System.currentTimeMillis()));
    }

    /**
     * Returns a random timestamp between fromYear and toYear.
     *
     * @param yearFrom from year
     * @param yearTo   to year
     * @return aTimestamp
     * @throws Exception illegal Date/Time
     */
    static public Timestamp makeRandomTimestamp(int yearFrom, int yearTo) throws Exception {
        int year = getRandom(yearFrom, yearTo);
        int month = getRandom(1, 12);
        int day = getRandom(1, lastDayOfMonth(year, month));
        int hour = getRandom(1, 23);
        int min = getRandom(1, 59);
        int sec = getRandom(1, 59);

        Timestamp ts = makeTimestamp(year, month, day, hour, min, sec);
        ts.setNanos(getRandom(0, 999) * 1000000);
        return ts;
    }

    /**
     * returns a random timestamp in a given year.
     *
     * @param year the year
     * @return aTimestamp in that year
     * @throws Exception illegal Date/Time
     */
    static public Timestamp makeRandomTimestamp(int year) throws Exception {
        return makeRandomTimestamp(year, year);
    }

    //
    // PRIVATE METHODS
    //
    static private GregorianCalendar makeGregorianCalendar(int year, int month, int day, int hr, int min, int sec)
            throws Exception {
        checkDate(year, month, day, hr, min, sec, 0);

        int mon;

        if (month == 1) mon = Calendar.JANUARY;
        else if (month == 2) mon = Calendar.FEBRUARY;
        else if (month == 3) mon = Calendar.MARCH;
        else if (month == 4) mon = Calendar.APRIL;
        else if (month == 5) mon = Calendar.MAY;
        else if (month == 6) mon = Calendar.JUNE;
        else if (month == 7) mon = Calendar.JULY;
        else if (month == 8) mon = Calendar.AUGUST;
        else if (month == 9) mon = Calendar.SEPTEMBER;
        else if (month == 10) mon = Calendar.OCTOBER;
        else if (month == 11) mon = Calendar.NOVEMBER;
        else mon = Calendar.DECEMBER;

        return new GregorianCalendar(year, mon, day, hr, min, sec);
    }


    static private void checkDate(
            int year, int month, int day,
            int hr, int min, int sec, int mSec) throws Exception {
        boolean ok = true;

        if ((hr < 0 || hr > 23)
                || (min < 0 || min > 59)
                || (sec < 0 || sec > 59)
                || (mSec < 0 || mSec > 999999)) {
            ok = false;
        } else if ((year < 1 || year > 9999)
                || (month < 1 || month > 12)) {
            ok = false;
        } else if (day < 1 || day > lastDayOfMonth(year, month)) {
            ok = false;
        }

        if (!ok) {
            throw new Exception("illegal Date/Time ("
                    + year + "-" + month + "-" + day + " "
                    + hr + ":" + min + ":" + sec + "." + mSec + ")");
        }
    }

    static private int lastDayOfMonth(int year, int month) {
        switch (month) {
            case 2:
                if (year % 400 == 0) {
                    return 29;
                }

                if (year % 100 == 0) {
                    return 28;
                }

                if (year % 4 == 0) {
                    return 29;
                }

                return 28;

            case 4:
            case 6:
            case 9:
            case 11:
                return 30;

            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
            default:
                return 31;
        }
    }

    static private int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

}
