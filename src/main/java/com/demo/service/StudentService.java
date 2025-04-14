package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.Dto.StudentDto;
import com.demo.entity.Student;
import com.demo.repo.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repo ;
	public StudentDto createStudent( @RequestBody StudentDto dto ) {
		Student s = new Student();
		BeanUtils.copyProperties(dto,s);
		Student saved = repo.save(s);
		BeanUtils.copyProperties(saved,dto);
		return dto ;

	}
	public void deletestudent(Long id) {
        repo.deleteById(id);		
	}
	public StudentDto updateStudent(long id, StudentDto dto) {
		// TODO Auto-generated method stub
		Student s = new Student();
        BeanUtils.copyProperties(dto, s);
		s.setId(id);  // 🔥 Overwrite the ID from body with the one from URL

        Student savedEntity = repo.save(s);
        BeanUtils.copyProperties(savedEntity, dto);

		return null;
	}
	//traditional approach
//	public List<StudentDto> findstudent() {
//        List<StudentDto> studentDtoList = new ArrayList<>();
//        Iterable<Student> students = repo.findAll();
//
//        for (Student student : students) {
//            StudentDto studentDto = new StudentDto();
//            BeanUtils.copyProperties(student, studentDto);
//            studentDtoList.add(studentDto);
//        }
//
//        return studentDtoList;
//
//	}
	
	//StreamApi functions 
	public List<StudentDto> findstudent() {
		List<Student> all = repo.findAll();
		List<StudentDto> collect = all.stream().map(s->convert(s)).collect(Collectors.toList());
		return collect;
	}
	public StudentDto convert(Student s) {
		StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(s, studentDto);
		return studentDto;
	
}}
