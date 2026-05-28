package com.restAPI.tutorial.controller;

import com.restAPI.tutorial.dto.AddStudentRequestDto;
import com.restAPI.tutorial.dto.DeleteStudentDto;
import com.restAPI.tutorial.dto.StudentDto;
import com.restAPI.tutorial.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/health")
    public String healthCheck(){
        return "OK";
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudent(){
        List<StudentDto> bodyData= studentService.getAllStudents();
        if(!bodyData.isEmpty()) return ResponseEntity.ok(bodyData);
        else return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        try {
            StudentDto bodyData = studentService.getStudentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(bodyData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> postStudent(@RequestBody AddStudentRequestDto studentRequestDto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(studentRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use, try another email");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteStudent(@RequestBody DeleteStudentDto deleteStudentDto){
        boolean success = studentService.deleteStudentById(deleteStudentDto);
        if(success) return ResponseEntity.ok("Data deleted successfully");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such data exists");
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudentById(@PathVariable Long id,
                                                        @RequestBody AddStudentRequestDto studentRequestDto){
        return ResponseEntity.ok(studentService.updateStudentById(id, studentRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        StudentDto updatedStudent = studentService.partiallyUpdateStudent(id, updates);
        return ResponseEntity.ok(updatedStudent);
    }
}
