package com.project.ces.controller;
import com.project.ces.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.ces.entity.CourseEntity;
import com.project.ces.service.CourseService;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;
    
    @PostMapping("/create/course")
    public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseEntity course) {
        CourseEntity createdCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping("/get/courses")
    public ResponseEntity<List<CourseEntity>> getAllCourses() {
        List<CourseEntity> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/get/course/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId) {
        ResponseData responseData = new ResponseData();
        CourseEntity course = courseService.getCourseById(courseId, responseData);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseData, HttpStatusCode.valueOf(responseData.getCode()));
        }
    }

    @PutMapping("/update/course/{courseId}")
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable Long courseId, @RequestBody CourseEntity course) {
        course.setCourseId(courseId); 
        CourseEntity updatedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId)
    {
        ResponseData responseData = new ResponseData();
        courseService.deleteCourse(courseId, responseData);
        return new ResponseEntity<>(responseData, HttpStatusCode.valueOf(responseData.getCode()));
    }
}
