package com.project.ces.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ces.entity.EnrollmentEntity;
import com.project.ces.repository.EnrollmentRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

@Service
public class EnrollmentCSVService {

    @Autowired
    private EnrollmentRepository enrollmentRepo;

    public void writeEnrollmentsToCsv(OutputStream outputStream) throws IOException {
        List<EnrollmentEntity> enrollments = enrollmentRepo.findAll();

        // Define CSV format with headers
        CSVFormat csvFormat = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                .setHeader("Enrollment ID", "Student Rollno", "Course ID", "Enrollment Date")
                .build();

        try (CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(outputStream), csvFormat)) {
            for (EnrollmentEntity enrollment : enrollments) {
                csvPrinter.printRecord(
                        enrollment.getEnrollmentId(),
                        enrollment.getStudent().getRollno(),
                        enrollment.getCourse().getCourseId(),
                        enrollment.getEnrollmentDate()
                );
            }
        }
    }
}
