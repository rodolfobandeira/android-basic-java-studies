package com.rodolfobandeira.scheduler.asynctask;

import android.os.AsyncTask;

import com.rodolfobandeira.scheduler.database.dao.StudentDAO;
import com.rodolfobandeira.scheduler.model.Student;

public class SaveStudentTask extends AsyncTask<Void, Void, Void> {
    private final StudentDAO dao;
    private final Student student;
    private final WhenSaveStudentListener listener;

    public SaveStudentTask(StudentDAO dao, Student student, WhenSaveStudentListener listener) {
        this.dao = dao;
        this.student = student;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.save(student);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.whenSave();
    }

    public interface WhenSaveStudentListener {
        void whenSave();
    }
}
