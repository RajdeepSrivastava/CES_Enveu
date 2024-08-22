package com.project.ces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.ces.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity , Long> {

}
