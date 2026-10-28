package com.pathsync.pathsync.controller;

import com.pathsync.pathsync.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/validate")
    public StudentValidationResponse validateStudent(
            @RequestParam String studentId) {

        boolean exists = studentRepository.existsByStudentId(
                studentId.trim().toUpperCase()
        );

        if (!exists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student ID not found"
            );
        }

        return new StudentValidationResponse(true, "Student ID recognized");
    }

    public record StudentValidationResponse(
            boolean valid,
            String message
    ) {
    }
}
