package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Dto.StudentDto;
import com.demo.service.StudentService;

import javax.validation.Valid;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService service;
	
//	@PostMapping("/create")
//	public ResponseEntity<StudentDto> createStudent( @RequestBody StudentDto dto ) {
//		StudentDto saved = service.createStudent(dto);
//		return new ResponseEntity<>(saved ,HttpStatus.CREATED);
//	}
	//above is correct this i am changing for validations
	@PostMapping("/create")
	public ResponseEntity<?> createStudent(
	    @Valid @RequestBody StudentDto dto,
	    BindingResult result
	) {
	    if (result.hasErrors()) {
	        return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
	    }
	    StudentDto saved = service.createStudent(dto);
	    return new ResponseEntity<>(saved, HttpStatus.CREATED);
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
	    public ResponseEntity<List<StudentDto>> findstudent(
	    		@RequestParam(name="pageNo",defaultValue="0",required=false)int pageNo,
	    		@RequestParam(name="pageSize",defaultValue="8",required=false)int pageSize,
	    		@RequestParam(name="sortBy",defaultValue="id",required=false)String sortBy,
	    		@RequestParam(name="sortDir",defaultValue="id",required=false)String sortDir
	    		) {
		  List<StudentDto> listall = service.findstudent(pageNo,pageSize,sortBy, sortDir);

	        return new ResponseEntity<>(listall, HttpStatus.OK);

	  }
		@GetMapping("/FindById")
	  public ResponseEntity<?> getbyid(@RequestParam long id) {
		  StudentDto fetchid =service.getbyid(id);
		  if (fetchid == null) {
		        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(fetchid, HttpStatus.OK);
	  }
		@GetMapping("/FindByCourse")
		public ResponseEntity<List<StudentDto>> getbycourse( @RequestParam String course ){
			List<StudentDto> fetchcourse =	service.getbycourse(course);
	        return new ResponseEntity<>(fetchcourse, HttpStatus.OK);
			
		}
		@GetMapping("/findByemailandcourse")
		public ResponseEntity<StudentDto> findByemailandcourse(@RequestParam String email, @RequestParam String course) {
		    StudentDto fetchemailandcourse = service.findByemailandcourse(email, course);
		    if (fetchemailandcourse == null) {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		    return new ResponseEntity<>(fetchemailandcourse, HttpStatus.OK);
		}
}
