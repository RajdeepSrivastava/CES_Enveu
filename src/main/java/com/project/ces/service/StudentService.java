package com.project.ces.service;

import java.util.List;
import java.util.Optional;

import com.project.ces.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.ces.entity.StudentEntity;
import com.project.ces.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepo;
	
	public StudentEntity saveStudent(StudentEntity student) {
		return studentRepo.save(student);
	}

	public List<StudentEntity> getAllStudents(){
		return studentRepo.findAll();
	}
	
    public StudentEntity getStudentByRollno(Long rollno, ResponseData responseData) {
        if (!studentRepo.existsById(rollno)) {
            responseData.setMessage("Student not found");
            responseData.setStatus("Failed");
            responseData.setCode(HttpStatus.NOT_FOUND.value());
        }
            return studentRepo.findById(rollno).orElse(null);
    }

    public void deleteStudent(Long rollno, ResponseData responseData) {
        if (!studentRepo.existsById(rollno))
        {
            responseData.setMessage("Student not found");
            responseData.setStatus("Failed");
            responseData.setCode(HttpStatus.NOT_FOUND.value());
        }
        else
        {
            studentRepo.deleteById(rollno);
            responseData.setMessage("Student with roll number " + rollno + " deleted successfully");
            responseData.setStatus("Success");
            responseData.setCode(HttpStatus.OK.value());
        }
    }
}
