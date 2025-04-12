package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Dto.StudentDto;
import com.demo.entity.Student;
import com.demo.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@PostMapping("/create")
	public ResponseEntity<StudentDto> createStudent( @RequestBody StudentDto dto ) {
		StudentDto saved = service.createStudent(dto);
		return new ResponseEntity<>(saved ,HttpStatus.CREATED);
	}
	
	

}
