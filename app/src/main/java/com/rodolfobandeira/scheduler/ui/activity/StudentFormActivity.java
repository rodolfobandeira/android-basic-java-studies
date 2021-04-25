package com.rodolfobandeira.scheduler.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rodolfobandeira.scheduler.dao.StudentDAO;
import com.rodolfobandeira.scheduler.R;
import com.rodolfobandeira.scheduler.model.Student;

import static com.rodolfobandeira.scheduler.ui.activity.ActivitiesConstants.APPBAR_TITLE;
import static com.rodolfobandeira.scheduler.ui.activity.ActivitiesConstants.APPBAR_TITLE_EDIT_STUDENT;
import static com.rodolfobandeira.scheduler.ui.activity.ActivitiesConstants.STUDENT_KEY;

public class StudentFormActivity extends AppCompatActivity {
    private EditText nameField;
    private EditText phoneField;
    private EditText emailField;
    private final StudentDAO dao = new StudentDAO();
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);
        initializeFields();
        loadStudent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_student_form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_student_form_menu_save) {
            finalizeSaveAction();
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadStudent() {
        Intent data = getIntent();

        if (data.hasExtra("student")) {
            student = (Student) data.getSerializableExtra(STUDENT_KEY);
            setTitle(APPBAR_TITLE_EDIT_STUDENT);
            populateFields();
        } else {
            setTitle(APPBAR_TITLE);
            student = new Student();
        }
    }

    private void populateFields() {
        nameField.setText(student.getName());
        phoneField.setText(student.getPhone());
        emailField.setText(student.getEmail());
    }

    private void finalizeSaveAction() {
        populateStudentFields();
        if (student.hasValidId()) {
            dao.edit(student);
        } else {
            dao.save(student);
        }
        finish();
    }

    private void initializeFields() {
        nameField = findViewById(R.id.activity_form_student_name);
        phoneField = findViewById(R.id.activity_form_student_phone);
        emailField = findViewById(R.id.activity_form_student_email);
    }

    private void save(Student student) {
        dao.save(student);

        Toast.makeText(
                StudentFormActivity.this,
                "Saved",
                Toast.LENGTH_SHORT
        ).show();

        finish();
    }

    private void populateStudentFields() {
        String name = nameField.getText().toString();
        String phone = phoneField.getText().toString();
        String email = emailField.getText().toString();

        student.setName(name);
        student.setPhone(phone);
        student.setEmail(email);
    }
}