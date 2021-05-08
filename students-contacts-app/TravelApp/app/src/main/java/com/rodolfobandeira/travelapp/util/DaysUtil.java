package com.rodolfobandeira.travelapp.util;

import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DaysUtil {

    public static String formatDays(Integer days) {
        String dayOrDays = (days > 0) ? " days":  "day";

        return String.format("%d %s", days, dayOrDays);
    }

    public static String formatDateRange(Integer days) {
        Calendar startDay = Calendar.getInstance();
        Calendar endDay = Calendar.getInstance();
        endDay.add(Calendar.DATE, days);

        SimpleDateFormat formatDate = new SimpleDateFormat("MMM d");
        String startDayText = formatDate.format(startDay.getTime());
        String endDayText = formatDate.format(endDay.getTime());
        Integer endYear = endDay.get(Calendar.YEAR);

        return String.format("%s - %s, %d", startDayText, endDayText, endYear);
    }
}
