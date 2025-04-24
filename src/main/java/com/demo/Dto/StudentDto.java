package com.demo.Dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentDto {

    private long id;

    @javax.validation.constraints.NotNull
    @javax.validation.constraints.Size(min = 2, max = 10, message = "Name must be between 2 and 10 characters")
    private String name;

    private String course;
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}