package com.demo.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentDto {

	private Long id ;
	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}
    @NotNull(message = "Name should not be null")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    private String course;

    @NotNull(message = "Email should not be null")
    @Email(message = "Enter a valid email")
    private String email;

    // Getters and Setters
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
