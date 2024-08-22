package com.project.ces.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ces.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity , Long>{

}
