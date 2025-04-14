package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Dto.StudentDto;
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
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteStudent( @RequestParam Long id ) {
		service.deletestudent(id);
	    return new ResponseEntity<>("Student with ID " + id + " deleted successfully.", HttpStatus.OK);

	}
	  @PutMapping("/update")
	    public ResponseEntity<StudentDto> updateStudent(@RequestParam long id, @RequestBody StudentDto dto) {
	        StudentDto updatedStudent = service.updateStudent(id, dto);
	        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
	    }
	  @GetMapping("/findallstudents")
	    public ResponseEntity<List<StudentDto>> findstudent() {
		  List<StudentDto> listall = service.findstudent();

	        return new ResponseEntity<>(listall, HttpStatus.OK);

	  }

}
