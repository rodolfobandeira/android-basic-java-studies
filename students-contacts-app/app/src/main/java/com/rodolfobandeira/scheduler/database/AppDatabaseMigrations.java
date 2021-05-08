package com.rodolfobandeira.scheduler.database;


import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

class AppDatabaseMigrations {
    public static final Migration[] ALL_MIGRATIONS = { new Migration(2, 3) { // OldVersion -> NewVersion
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
                        /*
                        SQL code to migrate the new data
                        */
            database.execSQL("ALTER TABLE student ADD COLUMN uuid TEXT");
        }
    }, new Migration(3, 4) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
                        /*
                        It is also possible to create a new table and migrate
                        the data over the new table.
                         */
            database.execSQL("UPDATE student SET phone = 99999999");
        }
    }, new Migration(4, 5) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student ADD COLUMN createdAt INTEGER");
        }
    }, new Migration(5, 6) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student ADD COLUMN nickname TEXT");
        }
    }};
}
