package com.pathsync.pathsync.repository;

import com.pathsync.pathsync.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentId(String studentId);

    boolean existsByStudentId(String studentId);
}