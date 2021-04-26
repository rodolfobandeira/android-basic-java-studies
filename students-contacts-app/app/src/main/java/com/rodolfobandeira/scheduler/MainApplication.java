package com.rodolfobandeira.scheduler;

import android.app.Application;

import com.rodolfobandeira.scheduler.dao.StudentDAO;
import com.rodolfobandeira.scheduler.model.Student;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        StudentDAO dao = new StudentDAO();

        seedStudents(dao);
    }

    private void seedStudents(StudentDAO dao) {
        for (int i = 0; i < 2; i++) {
            dao.save(new Student("test " + i, "123", "mail@example.com"));
        }
    }
}
