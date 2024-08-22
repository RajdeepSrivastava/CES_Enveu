package com.project.ces.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.ces.entity.EnrollmentEntity;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity , Long>{

	List<EnrollmentEntity> findByStudent_Rollno(Long rollno);
    List<EnrollmentEntity> findByCourse_CourseId(Long courseId);

}
