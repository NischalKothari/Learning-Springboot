package com.restAPI.tutorial.repositories;

import com.restAPI.tutorial.dto.DeleteStudentDto;
import com.restAPI.tutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}