package com.rodolfobandeira.scheduler.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rodolfobandeira.scheduler.R;
import com.rodolfobandeira.scheduler.model.Student;
import com.rodolfobandeira.scheduler.ui.StudentsListView;

import static com.rodolfobandeira.scheduler.ui.activity.ActivitiesConstants.STUDENT_KEY;

public class StudentListActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Students List";
    private final StudentsListView studentsListView = new StudentsListView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        setTitle(APPBAR_TITLE);
        setupNewStudentFloatingButton();
        setupStudentList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentsListView.updateStudentsList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_student_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_menu_remove_id) {
            studentsListView.confirmDeletion(item);
        }

        return super.onContextItemSelected(item);
    }

    private void setupNewStudentFloatingButton() {
        FloatingActionButton newStudentButton = findViewById(R.id.activity_student_list_fab_new_student);
        newStudentButton.setOnClickListener(view -> openCreateStudentForm());
    }

    private void openCreateStudentForm() {
        startActivity(new Intent(StudentListActivity.this, StudentFormActivity.class));
    }

    private void setupStudentList() {
        ListView studentList = findViewById(R.id.activity_student_list_listview);

        studentsListView.setupAdapter(studentList);
        setupItemClickListener(studentList);
        registerForContextMenu(studentList);
    }

    private void setupItemClickListener(ListView studentList) {
        studentList.setOnItemClickListener((adapterView, view, position, id) -> {
            Student selectedStudent = (Student) adapterView.getItemAtPosition(position);
            openEditStudentForm(selectedStudent);
        });
    }

    private void openEditStudentForm(Student selectedStudent) {
        Intent goToStudentFormActivity = new Intent(StudentListActivity.this, StudentFormActivity.class);
        goToStudentFormActivity.putExtra(STUDENT_KEY, selectedStudent);
        startActivity(goToStudentFormActivity);
    }
}