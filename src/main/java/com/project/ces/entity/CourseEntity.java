package com.project.ces.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CourseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long courseId;
	
	private String courseName;
	
	public CourseEntity(Long courseId, String courseName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}

	
	public CourseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCourseId() {
		return courseId;
	}
//	@JsonIgnore
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}
