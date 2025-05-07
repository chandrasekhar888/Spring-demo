package com.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.Dto.StudentDto;
import com.demo.entity.Student;
import com.demo.exception.ResourceNotFound;
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

		return dto;
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
	
	//StreamApi functions AND BELOW PAGINATION
//	public List<StudentDto> findstudent() {
//		List<Student> all = repo.findAll();
//		List<StudentDto> collect = all.stream().map(s->convert(s)).collect(Collectors.toList());
//		return collect;
//	}
//	public StudentDto convert(Student s) {
//		StudentDto studentDto = new StudentDto();
//        BeanUtils.copyProperties(s, studentDto);
//		return studentDto;
//	
//}
	//FOR PAGENATION 
	public List<StudentDto> findstudent(int pageNo, int pageSize, String sortBy, String sortDir) {
	    Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable pageable=  PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Student> all = repo.findAll(pageable);
		List<Student> content = all.getContent(); //converting page of students to list of students
		
		List<StudentDto> collect = all.stream().map(s->convert(s)).collect(Collectors.toList());
		return collect;
	}
	public StudentDto convert(Student s) {
		StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(s, studentDto);
		return studentDto;
	
}
	public StudentDto getbyid(long id) {
		Student orElseThrow = repo.findById(id).orElseThrow(
				()->new ResourceNotFound("record not found"+id)
				);
		
	//	Optional<Student> byId = repo.findById(id);
//		if(byId.isPresent()) {
//		Student student = byId.get();//optional class object convert to entity by using get 
//		return convert(student);
//		}
		return null;
		// TODO Auto-generated method stub
		
	}
	public List<StudentDto> getbycourse(String course) {
		List<Student> bycourse = repo.findBycourse(course);
		List<StudentDto> ListCourse = bycourse.stream().map(s->convert(s)).collect(Collectors.toList());
		return ListCourse;
		// TODO Auto-generated method stub
		
	}
	public StudentDto findByemailandcourse(String email, String course) {
	    Optional<Student> byemailandcourse = repo.findByEmailAndCourse(email, course);
	    if (byemailandcourse.isPresent()) {
	        return convert(byemailandcourse.get());
	    }
	    return null;
	}

	

}
