package com.rodolfobandeira.scheduler.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.rodolfobandeira.scheduler.R;
import com.rodolfobandeira.scheduler.asynctask.SaveStudentTask;
import com.rodolfobandeira.scheduler.database.AppDatabase;
import com.rodolfobandeira.scheduler.database.dao.StudentDAO;
import com.rodolfobandeira.scheduler.model.Student;

import static com.rodolfobandeira.scheduler.ui.activity.ActivitiesConstants.APPBAR_TITLE;
import static com.rodolfobandeira.scheduler.ui.activity.ActivitiesConstants.APPBAR_TITLE_EDIT_STUDENT;
import static com.rodolfobandeira.scheduler.ui.activity.ActivitiesConstants.STUDENT_KEY;

public class StudentFormActivity extends AppCompatActivity {
    private EditText firstNameField;
    private EditText lastNameField;
    private EditText phoneField;
    private EditText emailField;
    private EditText nicknameField;
    private StudentDAO dao;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);

        AppDatabase database = AppDatabase.getInstance(this);
        dao = database.getRoomStudentDAO();

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
        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        phoneField.setText(student.getPhone());
        emailField.setText(student.getEmail());
        nicknameField.setText(student.getNickname());
    }

    private void finalizeSaveAction() {
        populateStudentFields();
        if (student.hasValidId()) {
            // This is still synchronously to compare the differences
            dao.edit(student);
        } else {
            // This performs async
            new SaveStudentTask(dao, student, this::finish).execute();
        }
        finish();
    }

    private void initializeFields() {
        firstNameField = findViewById(R.id.activity_form_student_first_name);
        lastNameField = findViewById(R.id.activity_form_student_last_name);
        phoneField = findViewById(R.id.activity_form_student_phone);
        emailField = findViewById(R.id.activity_form_student_email);
        nicknameField = findViewById(R.id.activity_form_student_nickname);
    }

    private void populateStudentFields() {
        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();
        String phone = phoneField.getText().toString();
        String email = emailField.getText().toString();
        String nickname = nicknameField.getText().toString();

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setPhone(phone);
        student.setEmail(email);
        student.setNickname(nickname);
    }
}