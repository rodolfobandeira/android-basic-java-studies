package com.rodolfobandeira.scheduler.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rodolfobandeira.scheduler.model.Student;

import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    void save(Student student);

    @Query("SELECT * FROM student")
    List<Student> getAll();

    @Delete
    void remove(Student student);

    @Update
    void edit(Student student);
}
