package com.rodolfobandeira.scheduler.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Entity
public class Student implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String nickname;
    private Calendar createdAt = Calendar.getInstance();
    private String uuid = UUID.randomUUID().toString();

    @NonNull
    @Override
    public String toString() {
        return firstName + " " + lastName + " - " + phone;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean hasValidId() {
        return id > 0;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String formattedDate() {
        SimpleDateFormat dateFormatted = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormatted.format(createdAt.getTime());
    }
}
