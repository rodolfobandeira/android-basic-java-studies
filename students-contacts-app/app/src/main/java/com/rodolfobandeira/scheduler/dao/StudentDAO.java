package com.rodolfobandeira.scheduler.dao;

import com.rodolfobandeira.scheduler.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private final static List<Student> students = new ArrayList<>();
    private static int idsCounter = 1;

    public void save(Student student) {
        student.setId(idsCounter);
        students.add(student);
        updateIds();
    }

    private void updateIds() {
        idsCounter++;
    }

    public void edit(Student student) {
        Student studentFound = findStudentById(student);

        updateStudent(student, studentFound);
    }

    private Student findStudentById(Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                return s;
            }
        }
        return null;
    }

    private void updateStudent(Student student, Student studentFound) {
        if (studentFound != null) {
            int studentPosition = students.indexOf(studentFound);
            students.set(studentPosition, student);
        }
    }

    public List<Student> getAll() {
        return new ArrayList<>(students);
    }

    public void remove(Student selectedStudent) {
        Student student = findStudentById(selectedStudent);
        if (student != null) {
            students.remove(student);
        }
    }
}
