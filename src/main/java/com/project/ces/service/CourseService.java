package com.project.ces.service;

import com.project.ces.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.ces.entity.CourseEntity;
import com.project.ces.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    public List<CourseEntity> getAllCourses() {
        return courseRepo.findAll();
    }

    public CourseEntity getCourseById(Long courseId, ResponseData responseData) {
        if (!courseRepo.existsById(courseId)) {
            responseData.setMessage("Student not found");
            responseData.setStatus("Failed");
            responseData.setCode(HttpStatus.NOT_FOUND.value());
        }
        return courseRepo.findById(courseId).orElse(null);
    }

    public CourseEntity saveCourse(CourseEntity course) {
        return courseRepo.save(course);
    }

    public void deleteCourse(Long courseId, ResponseData responseData) {
        if (!courseRepo.existsById(courseId))
        {
            responseData.setMessage("Course not found");
            responseData.setStatus("Failed");
            responseData.setCode(HttpStatus.NOT_FOUND.value());
        }
        else
        {
            courseRepo.deleteById(courseId);
            responseData.setMessage("Course with course ID " + courseId + " deleted successfully");
            responseData.setStatus("Success");
            responseData.setCode(HttpStatus.OK.value());
        }
    }
}
