package com.project.ces.controller;

import com.project.ces.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.ces.entity.CourseEntity;
import com.project.ces.entity.EnrollmentEntity;
import com.project.ces.entity.StudentEntity;
import com.project.ces.repository.EnrollmentRepository;
import com.project.ces.service.CourseService;
import com.project.ces.service.EnrollmentService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/enrollments")
    public ResponseEntity<List<EnrollmentEntity>> getAllEnrollments() {
        List<EnrollmentEntity> enrollments = enrollmentService.getAllEnrollments();
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

    @PostMapping("/create/enrollment")
    public EnrollmentEntity createEnrollment(@RequestBody EnrollmentEntity enrollment) {
        return enrollmentService.saveEnrollment(enrollment);
    }


    @DeleteMapping("/delete/enrollment/{enrollmentId}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable Long enrollmentId) {
        ResponseData responseData = new ResponseData();
        enrollmentService.deleteEnrollment(enrollmentId, responseData);
        return new ResponseEntity<>(responseData, HttpStatusCode.valueOf(responseData.getCode()));
    }

    @GetMapping("/students/{rollno}/courses")
    public ResponseEntity<List<CourseEntity>> getCoursesByStudentRollno(@PathVariable Long rollno) {
        List<CourseEntity> courses = enrollmentService.getCoursesByStudentRollno(rollno);
        if (courses != null && !courses.isEmpty()) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/courses/{courseId}/students")
    public ResponseEntity<List<StudentEntity>> getStudentsByCourseId(@PathVariable Long courseId) {
        List<StudentEntity> students = enrollmentService.getStudentsByCourseId(courseId);
        if (students != null && !students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
