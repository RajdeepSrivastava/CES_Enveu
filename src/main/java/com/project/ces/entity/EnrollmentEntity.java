package com.project.ces.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EnrollmentEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "student_rollno", referencedColumnName = "rollno")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private CourseEntity course;

    private LocalDate enrollmentDate;

    public Long getEnrollmentId() {
		return enrollmentId;
	}

	public EnrollmentEntity(Long enrollmentId, StudentEntity student, CourseEntity course, LocalDate enrollmentDate) {
		super();
		this.enrollmentId = enrollmentId;
		this.student = student;
		this.course = course;
		this.enrollmentDate = enrollmentDate;
	}
	
	public EnrollmentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	@JsonIgnore
	public void setEnrollmentId(Long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}
	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	@Override
	public String toString() {
		return "EnrollmentEntity [enrollmentId=" + enrollmentId + ", student=" + student + ", course=" + course
				+ ", enrollmentDate=" + enrollmentDate + "]";
	}



    
}
