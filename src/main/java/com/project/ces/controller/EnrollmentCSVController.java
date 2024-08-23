package com.project.ces.controller;

import com.opencsv.CSVWriter;
import com.project.ces.entity.EnrollmentEntity;
import com.project.ces.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/csv/")
public class EnrollmentCSVController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportEnrollmentsToCSV() {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8))) {

            csvWriter.writeNext(new String[]{"ID", "Student ID", "Course ID", "Enrollment Date"});

            List<EnrollmentEntity> enrollments = enrollmentService.getAllEnrollments();

            for (EnrollmentEntity enrollment : enrollments) {
                csvWriter.writeNext(new String[]{
                        enrollment.getEnrollmentId().toString(),
                        String.valueOf(enrollment.getStudent().getRollno()),
                        enrollment.getCourse().getCourseId().toString(),
                        enrollment.getEnrollmentDate().toString()
                });
            }
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=enrollments.csv");
            headers.set(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name());

            return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}



