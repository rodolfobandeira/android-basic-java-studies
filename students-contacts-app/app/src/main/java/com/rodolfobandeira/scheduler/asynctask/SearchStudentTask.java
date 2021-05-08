package com.rodolfobandeira.scheduler.asynctask;

import android.os.AsyncTask;

import com.rodolfobandeira.scheduler.database.dao.StudentDAO;
import com.rodolfobandeira.scheduler.model.Student;
import com.rodolfobandeira.scheduler.ui.adapter.StudentListAdapter;

import java.util.List;

public class SearchStudentTask extends AsyncTask<Void, Void, List<Student>> {
    private final StudentDAO dao;
    private final StudentListAdapter adapter;

    public SearchStudentTask(StudentDAO dao, StudentListAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Student> doInBackground(Void[] objects) {
        List<Student> allStudents = dao.getAll();
        return allStudents;
    }

    @Override
    protected void onPostExecute(List<Student> allStudents) {
        super.onPostExecute(allStudents);

        adapter.update(allStudents);
    }
}
