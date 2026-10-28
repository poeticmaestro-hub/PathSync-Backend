package com.pathsync.pathsync.repository;

import com.pathsync.pathsync.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}