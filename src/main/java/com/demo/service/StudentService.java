package com.demo.service;

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
}
