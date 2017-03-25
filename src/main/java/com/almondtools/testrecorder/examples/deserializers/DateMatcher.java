package com.almondtools.testrecorder.examples.deserializers;

import java.util.Calendar;
import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class DateMatcher extends TypeSafeMatcher<Date> {

    private int day;
    private int month;
    private int year;

    public DateMatcher withDay(int day) {
        this.day = day;
        return this;
    }

    public DateMatcher withMonth(int month) {
        this.month = month;
        return this;
    }

    public DateMatcher withYear(int year) {
        this.year = year;
        return this;
    }

    @Override
    public void describeTo(Description description) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        description.appendText("equal to ").appendValue(calendar);
    }

    @Override
    protected boolean matchesSafely(Date item) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(item);

        return calendar.get(Calendar.DAY_OF_MONTH) == day
            && calendar.get(Calendar.MONTH) == month
            && calendar.get(Calendar.YEAR) == year;
    }

    public static DateMatcher matchesDate() {
        return new DateMatcher();
    }
}
