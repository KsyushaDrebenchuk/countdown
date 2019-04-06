package android.by.itacademy.countdown;

import android.util.Log;

import java.util.Calendar;

public class Converter {

    private int yearCurrent;
    private int monthCurrent;
    private int dayCurrent;
    private int hourCurrent;
    private int minutesCurrent;
    private int secondsCurrent;

    public long covertDataToMillis(int year, int month, int day, int hour, int minutes, int seconds) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendarSet = Calendar.getInstance();

        calendarSet.clear();
        calendarSet.set(year, month, day, hour, minutes, seconds);
        long diff = calendarSet.getTimeInMillis() - calendar.getTimeInMillis();

        Log.e("AAA", String.valueOf(diff));
        return diff;
    }
}
