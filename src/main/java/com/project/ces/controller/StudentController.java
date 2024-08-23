package com.project.ces.controller;

import java.util.List;

import com.project.ces.model.ResponseData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ces.entity.StudentEntity;
import com.project.ces.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
    @PostMapping("/create/student")
    public ResponseEntity<StudentEntity> createStudent(@Valid @RequestBody StudentEntity student) {
        StudentEntity createdStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }
	
	@GetMapping("/get/students")
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        List<StudentEntity> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
	
	@GetMapping("/get/student/{rollno}")
    public ResponseEntity<?> getStudentByRollno(@PathVariable Long rollno) {
        ResponseData responseData = new ResponseData();
        StudentEntity student = studentService.getStudentByRollno(rollno, responseData);
        if(student == null) {
            return new ResponseEntity<>(responseData, HttpStatusCode.valueOf(responseData.getCode()));
        }
        else {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }
	
	@PutMapping("/update/student/{rollno}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable Long rollno, @RequestBody StudentEntity student) {
        student.setRollno(rollno); 
        StudentEntity updatedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }
	
	@DeleteMapping("/delete/student/{rollno}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long rollno) {
        ResponseData responseData = new ResponseData();
        studentService.deleteStudent(rollno, responseData);
        return new ResponseEntity<>(responseData, HttpStatusCode.valueOf(responseData.getCode()));
    }
}
