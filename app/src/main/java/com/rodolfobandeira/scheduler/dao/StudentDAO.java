package com.rodolfobandeira.scheduler.dao;

import com.rodolfobandeira.scheduler.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private final static List<Student> students = new ArrayList<>();

    public void save(Student newStudent) {
        students.add(newStudent);
    }

    public List<Student> getAll() {
        return new ArrayList<>(students);
    }
}
