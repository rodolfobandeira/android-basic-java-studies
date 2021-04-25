package com.rodolfobandeira.scheduler.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rodolfobandeira.scheduler.dao.StudentDAO;
import com.rodolfobandeira.scheduler.R;
import com.rodolfobandeira.scheduler.model.Student;

import java.util.Collections;
import java.util.List;

import static com.rodolfobandeira.scheduler.ui.activity.ActivitiesConstants.STUDENT_KEY;

public class StudentListActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Students List";
    private final StudentDAO dao = new StudentDAO();
    private ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        setTitle(APPBAR_TITLE);
        setupNewStudentFloatingButton();
        setupStudentList();

        for (int i = 0; i < 30 ; i++) {
            dao.save(new Student("test " + i, "123", "mail@example.com"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStudentsList();
    }

    private void updateStudentsList() {
        adapter.clear();
        List<Student> studentList = dao.getAll();
        Collections.reverse(studentList);
        adapter.addAll(studentList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_student_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_menu_remove_id) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Student selectedStudent = adapter.getItem(menuInfo.position);
            remove(selectedStudent);
        }

        return super.onContextItemSelected(item);
    }

    private void setupNewStudentFloatingButton() {
        FloatingActionButton newStudentButtom = findViewById(R.id.activity_student_list_fab_new_student);
        newStudentButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateStudentForm();
            }
        });
    }

    private void openCreateStudentForm() {
        startActivity(new Intent(StudentListActivity.this, StudentFormActivity.class));
    }

    private void setupStudentList() {
        ListView studentList = findViewById(R.id.activity_student_list_listview);

        setupAdapter(studentList);
        setupItemClickListener(studentList);
        registerForContextMenu(studentList);
    }

    private void remove(Student selectedStudent) {
        dao.remove(selectedStudent);
        adapter.remove(selectedStudent);
        Toast.makeText(StudentListActivity.this,
                selectedStudent.getName() + " was removed",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void setupItemClickListener(ListView studentList) {
        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Student selectedStudent = (Student) adapterView.getItemAtPosition(position);
                openEditStudentForm(selectedStudent);
            }
        });
    }

    private void openEditStudentForm(Student selectedStudent) {
        Intent goToStudentFormActivity = new Intent(StudentListActivity.this, StudentFormActivity.class);
        goToStudentFormActivity.putExtra(STUDENT_KEY, selectedStudent);
        startActivity(goToStudentFormActivity);
    }

    private void setupAdapter(ListView studentList) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1
        );
        studentList.setAdapter(adapter);
    }
}