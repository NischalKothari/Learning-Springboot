package com.restAPI.tutorial.service;

import com.restAPI.tutorial.dto.AddStudentRequestDto;
import com.restAPI.tutorial.dto.DeleteStudentDto;
import com.restAPI.tutorial.dto.StudentDto;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto studentRequestDto);

    boolean deleteStudentById(DeleteStudentDto deleteStudentDto);

    StudentDto updateStudentById(Long id, AddStudentRequestDto studentRequestDto);

    StudentDto partiallyUpdateStudent(Long id, Map<String, Object> updates);
}
