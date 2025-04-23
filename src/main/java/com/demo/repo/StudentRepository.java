package com.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {

	List<Student> findBycourse(String course);
	Optional<Student> findByEmailAndCourse( String email, String course);
}
