package com.rodolfobandeira.scheduler.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rodolfobandeira.scheduler.dao.StudentDAO;
import com.rodolfobandeira.scheduler.R;
import com.rodolfobandeira.scheduler.model.Student;

public class StudentFormActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "New Student";
    private EditText nameField;
    private EditText phoneField;
    private EditText emailField;
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);
        setTitle(APPBAR_TITLE);
        initializeFields();
        setupSaveButton();
    }

    private void setupSaveButton() {
        Button saveButton = findViewById(R.id.activity_form_student_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student newStudent = createStudent();
                save(newStudent);
            }
        });
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

    private Student createStudent() {
        String name = nameField.getText().toString();
        String phone = phoneField.getText().toString();
        String email = emailField.getText().toString();

        return new Student(name, phone, email);
    }
}