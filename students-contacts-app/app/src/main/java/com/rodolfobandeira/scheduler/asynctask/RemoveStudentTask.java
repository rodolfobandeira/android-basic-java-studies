package com.rodolfobandeira.scheduler.asynctask;

import android.os.AsyncTask;

import com.rodolfobandeira.scheduler.database.dao.StudentDAO;
import com.rodolfobandeira.scheduler.model.Student;
import com.rodolfobandeira.scheduler.ui.adapter.StudentListAdapter;

public class RemoveStudentTask extends AsyncTask<Void, Void, Void> {
    private final StudentListAdapter adapter;
    private final StudentDAO dao;
    private final Student student;

    public RemoveStudentTask(StudentDAO dao, StudentListAdapter adapter, Student student) {
        this.adapter = adapter;
        this.dao = dao;
        this.student = student;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.remove(student);

        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        adapter.remove(student);
    }
}
