package com.rodolfobandeira.scheduler.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.rodolfobandeira.scheduler.asynctask.RemoveStudentTask;
import com.rodolfobandeira.scheduler.asynctask.SearchStudentTask;
import com.rodolfobandeira.scheduler.database.AppDatabase;
import com.rodolfobandeira.scheduler.database.dao.StudentDAO;
import com.rodolfobandeira.scheduler.model.Student;
import com.rodolfobandeira.scheduler.ui.adapter.StudentListAdapter;

public class StudentsListView {
    private static final String APP_DB_NAME = "contacts.db";
    private final StudentDAO dao;
    private final StudentListAdapter adapter;
    private final Context context;

    public StudentsListView(Context context) {
        this.context = context;
        this.adapter = new StudentListAdapter(this.context);

        AppDatabase database = AppDatabase.getInstance(context);
        this.dao = database.getRoomStudentDAO();
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
        new SearchStudentTask(dao, adapter).execute(); // Make this action async
    }

    public void remove(Student selectedStudent) {
        new RemoveStudentTask(dao, adapter, selectedStudent).execute(); // Make this action async
        Toast.makeText(this.context,
                selectedStudent.getFirstName() + " was removed",
                Toast.LENGTH_SHORT
        ).show();
    }
}



