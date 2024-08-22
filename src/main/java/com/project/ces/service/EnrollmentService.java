package com.project.ces.service;

import com.project.ces.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.ces.entity.CourseEntity;
import com.project.ces.entity.EnrollmentEntity;
import com.project.ces.entity.StudentEntity;
import com.project.ces.repository.CourseRepository;
import com.project.ces.repository.EnrollmentRepository;
import com.project.ces.repository.StudentRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepo;
    
    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;

    public List<EnrollmentEntity> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }

    public EnrollmentEntity saveEnrollment(EnrollmentEntity enrollment) {
        StudentEntity student = studentRepo.findById(enrollment.getStudent().getRollno())
            .orElseThrow(() -> new IllegalArgumentException("Invalid student rollno: " + enrollment.getStudent().getRollno()));

        CourseEntity course = courseRepo.findById(enrollment.getCourse().getCourseId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid courseId: " + enrollment.getCourse().getCourseId()));

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        
        return enrollmentRepo.save(enrollment);
    }
    
    
    public void deleteEnrollment(Long enrollmentId, ResponseData responseData) {
        if (!enrollmentRepo.existsById(enrollmentId))
        {
            responseData.setMessage("Enrollment not found");
            responseData.setStatus("Failed");
            responseData.setCode(HttpStatus.NOT_FOUND.value());
        }
        else
        {
            enrollmentRepo.deleteById(enrollmentId);
            responseData.setMessage("Enrollment data deleted successfully");
            responseData.setStatus("Success");
            responseData.setCode(HttpStatus.OK.value());
        }
    }
    
    public List<CourseEntity> getCoursesByStudentRollno(Long rollno) {
        List<EnrollmentEntity> enrollments = enrollmentRepo.findByStudent_Rollno(rollno);
        return enrollments.stream().map(EnrollmentEntity::getCourse).collect(Collectors.toList());
    }
    
    public List<StudentEntity> getStudentsByCourseId(Long courseId) {
        List<EnrollmentEntity> enrollments = enrollmentRepo.findByCourse_CourseId(courseId);
        return enrollments.stream().map(EnrollmentEntity::getStudent).collect(Collectors.toList()); 
    }
    

   
    
    
}
