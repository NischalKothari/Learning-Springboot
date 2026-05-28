package com.restAPI.tutorial.service.impl;

import com.restAPI.tutorial.dto.AddStudentRequestDto;
import com.restAPI.tutorial.dto.DeleteStudentDto;
import com.restAPI.tutorial.dto.StudentDto;
import com.restAPI.tutorial.entity.Student;
import com.restAPI.tutorial.repositories.StudentRepository;
import com.restAPI.tutorial.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Override
    public List<StudentDto> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtoList = students
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
        return studentDtoList;
    }

    @Override
    public StudentDto getStudentById(Long id){
        Student stu = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student with id "+id+" not found"));
        return modelMapper.map(stu, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto studentRequestDto){
        Student newStu = modelMapper.map(studentRequestDto, Student.class);
        Student stu = studentRepository.save(newStu);
        return modelMapper.map(stu, StudentDto.class);
    }

    @Override
    public boolean deleteStudentById(DeleteStudentDto deleteStudentDto){
        try{
            String mail = deleteStudentDto.getEmail();
            Student stu = studentRepository.findByEmail(mail).orElseThrow(() -> new IllegalArgumentException("Student with mail id "+mail+" not found"));
            studentRepository.delete(stu);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public StudentDto updateStudentById(Long id, AddStudentRequestDto studentRequestDto){
        Student stu = studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student with id "+id+" not found"));
        modelMapper.map(studentRequestDto,stu);
        stu = studentRepository.save(stu);
        return modelMapper.map(stu,StudentDto.class);
    }

    @Override
    public StudentDto partiallyUpdateStudent(Long id, Map<String, Object> updates){
        Student stu = studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student with id "+id+" not found"));
        try {
            objectMapper.updateValue(stu, updates);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update student fields", e);
        }
        Student updatedStu = studentRepository.save(stu);
        return modelMapper.map(updatedStu, StudentDto.class);
    }
}
