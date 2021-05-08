package com.rodolfobandeira.scheduler.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class CalendarConverter {

    @TypeConverter
    public Long toLong(Calendar value) {
        if (value != null) {
            return value.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar toCalendar(Long value) {
        Calendar createdAt = Calendar.getInstance();

        if (value != null) {
            createdAt.setTimeInMillis(value);
        }

        return createdAt;
    }
}
