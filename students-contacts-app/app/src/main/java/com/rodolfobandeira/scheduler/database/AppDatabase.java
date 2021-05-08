package com.rodolfobandeira.scheduler.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.rodolfobandeira.scheduler.database.converter.CalendarConverter;
import com.rodolfobandeira.scheduler.database.dao.StudentDAO;
import com.rodolfobandeira.scheduler.model.Student;

import org.jetbrains.annotations.NotNull;

import static com.rodolfobandeira.scheduler.database.AppDatabaseMigrations.ALL_MIGRATIONS;

@Database(entities = {Student.class}, version = 6, exportSchema = false)
@TypeConverters({CalendarConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "contacts.db";

    public abstract StudentDAO getRoomStudentDAO();

    public static AppDatabase getInstance(Context context) {
        return Room
                .databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .addMigrations(ALL_MIGRATIONS)
                .build();
    }
}
