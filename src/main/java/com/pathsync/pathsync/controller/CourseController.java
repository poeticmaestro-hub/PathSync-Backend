
package com.pathsync.pathsync.controller;

import com.pathsync.pathsync.model.Course;
import com.pathsync.pathsync.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Retrieve all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Retrieve a course by ID
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found"));
    }

    // Create a new course with validation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseRepository.save(course);
    }

    // Update an existing course with validation
    @PutMapping("/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody Course updatedCourse) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found"));

        course.setCourseCode(updatedCourse.getCourseCode());
        course.setTitle(updatedCourse.getTitle());
        course.setCreditHours(updatedCourse.getCreditHours());

        return courseRepository.save(course);
    }

    // Delete a course
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Course not found");
        }

        courseRepository.deleteById(id);
    }
}
