package com.rodolfobandeira.scheduler.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rodolfobandeira.scheduler.dao.StudentDAO;
import com.rodolfobandeira.scheduler.R;

public class StudentListActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Students List";
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        setTitle(APPBAR_TITLE);
        setupNewStudentFloatingButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupStudentList();
    }

    private void setupNewStudentFloatingButton() {
        FloatingActionButton newStudentButtom = findViewById(R.id.activity_student_list_fab_new_student);
        newStudentButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStudentFormActivity();
            }
        });
    }

    private void openStudentFormActivity() {
        startActivity(new Intent(StudentListActivity.this, StudentFormActivity.class));
    }

    private void setupStudentList() {
        ListView studentList = findViewById(R.id.activity_student_list_listview);
        studentList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.getAll()
        ));
    }
}