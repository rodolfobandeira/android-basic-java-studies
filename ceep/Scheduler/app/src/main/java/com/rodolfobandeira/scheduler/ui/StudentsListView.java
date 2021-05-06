package com.rodolfobandeira.scheduler.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.rodolfobandeira.scheduler.dao.StudentDAO;
import com.rodolfobandeira.scheduler.model.Student;
import com.rodolfobandeira.scheduler.ui.activity.StudentListActivity;
import com.rodolfobandeira.scheduler.ui.adapter.StudentListAdapter;

public class StudentsListView {
    private final StudentDAO dao;
    private final StudentListAdapter adapter;
    private final Context context;

    public StudentsListView(Context context) {
        this.context = context;
        this.adapter = new StudentListAdapter(this.context);
        this.dao = new StudentDAO();
    }

    public void setupAdapter(ListView studentList) {
        studentList.setAdapter(adapter);
    }

    public void confirmDeletion(@NonNull final MenuItem item) {
        new AlertDialog
                .Builder(this.context)
                .setTitle("Removing student")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Student selectedStudent = adapter.getItem(menuInfo.position);
                    remove(selectedStudent);
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void updateStudentsList() {
        adapter.update(dao.getAll());
    }

    public void remove(Student selectedStudent) {
        dao.remove(selectedStudent);
        adapter.remove(selectedStudent);
        Toast.makeText(this.context,
                selectedStudent.getName() + " was removed",
                Toast.LENGTH_SHORT
        ).show();
    }
}



