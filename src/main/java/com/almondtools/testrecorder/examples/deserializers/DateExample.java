package com.almondtools.testrecorder.examples.deserializers;

import java.util.Calendar;
import java.util.Date;

import net.amygdalum.testrecorder.profile.Recorded;

public class DateExample {
    
    private Date date;
    
    public DateExample() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
    }
    
    public Date getDate() {
        return date;
    }
    
    public static void main(String[] args) {
        DateExample example = new DateExample();
        System.out.println(example.getDate());
        System.out.println(example.addDay());
        System.out.println(example.addMonth());
        System.out.println(example.addYear());
    }

    @Recorded
    Date addDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    @Recorded
    Date addMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    @Recorded
    Date addYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

}
