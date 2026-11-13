package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentOperations;

    public StudentController(StudentService studentOperations) {
        this.studentOperations = studentOperations;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student studentPayload) {
        Student createdRecord = studentOperations.addStudent(studentPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentOperations.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long studentId) {
        return ResponseEntity.ok(studentOperations.getStudentById(studentId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long studentId,
                                                 @RequestBody Student studentPayload) {
        return ResponseEntity.ok(studentOperations.updateStudent(studentId, studentPayload));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long studentId) {
        studentOperations.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course/{course}")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable("course") String courseName) {
        return ResponseEntity.ok(studentOperations.getStudentsByCourse(courseName));
    }
}
