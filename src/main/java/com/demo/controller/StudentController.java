package com.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.demo.Dto.StudentDto;
import com.demo.service.StudentService;

import javax.validation.Valid;

@RestController
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService service;

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDto dto, BindingResult result) {
        logger.info("Received request to create student: {}", dto);

        if (result.hasErrors()) {
            String errorMsg = result.getFieldError().getDefaultMessage();
            logger.error("Validation failed: {}", errorMsg);
            return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
        }

        StudentDto saved = service.createStudent(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id) {
        logger.info("Received request to delete student with ID: {}", id);

        service.deletestudent(id);
        logger.info("Student with ID {} deleted successfully", id);

        return new ResponseEntity<>("Student with ID " + id + " deleted successfully.", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StudentDto> updateStudent(@RequestParam long id, @RequestBody StudentDto dto) {
        logger.info("Received request to update student with ID: {}", id);

        StudentDto updatedStudent = service.updateStudent(id, dto);
        logger.info("Student with ID {} updated successfully", id);

        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @GetMapping("/findallstudents")
    public ResponseEntity<List<StudentDto>> findstudent(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "8", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "id", required = false) String sortDir) {

        logger.info("Fetching all students: pageNo={}, pageSize={}, sortBy={}, sortDir={}", pageNo, pageSize, sortBy, sortDir);

        List<StudentDto> listall = service.findstudent(pageNo, pageSize, sortBy, sortDir);
        logger.info("Fetched {} students", listall.size());

        return new ResponseEntity<>(listall, HttpStatus.OK);
    }

    @GetMapping("/FindById")
    public ResponseEntity<?> getbyid(@RequestParam long id) {
        logger.info("Fetching student by ID: {}", id);

        StudentDto fetchid = service.getbyid(id);
        if (fetchid == null) {
            logger.warn("Student with ID {} not found", id);
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }

        logger.info("Student with ID {} found", id);
        return new ResponseEntity<>(fetchid, HttpStatus.OK);
    }

    @GetMapping("/FindByCourse")
    public ResponseEntity<List<StudentDto>> getbycourse(@RequestParam String course) {
        logger.info("Fetching students by course: {}", course);

        List<StudentDto> fetchcourse = service.getbycourse(course);
        logger.info("Fetched {} students for course: {}", fetchcourse.size(), course);

        return new ResponseEntity<>(fetchcourse, HttpStatus.OK);
    }

    @GetMapping("/findByemailandcourse")
    public ResponseEntity<StudentDto> findByemailandcourse(@RequestParam String email, @RequestParam String course) {
        logger.info("Fetching student by email: {} and course: {}", email, course);

        StudentDto fetchemailandcourse = service.findByemailandcourse(email, course);
        if (fetchemailandcourse == null) {
            logger.warn("No student found with email {} and course {}", email, course);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Student found with email {} and course {}", email, course);
        return new ResponseEntity<>(fetchemailandcourse, HttpStatus.OK);
    }
}
