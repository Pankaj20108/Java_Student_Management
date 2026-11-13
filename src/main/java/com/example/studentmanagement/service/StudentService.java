package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.exception.StudentNotFoundException;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository records;

    public StudentService(StudentRepository records) {
        this.records = records;
    }

    public Student addStudent(Student studentPayload) {
        studentPayload.setId(null);
        return records.save(studentPayload);
    }

    public List<Student> getAllStudents() {
        return records.findAll();
    }

    public Student getStudentById(Long studentId) {
        return records.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }

    public Student updateStudent(Long studentId, Student newDetails) {
        Student storedRecord = getStudentById(studentId);
        storedRecord.setName(newDetails.getName());
        storedRecord.setEmail(newDetails.getEmail());
        storedRecord.setCourse(newDetails.getCourse());
        storedRecord.setAge(newDetails.getAge());
        return records.save(storedRecord);
    }

    public void deleteStudent(Long studentId) {
        Student storedRecord = getStudentById(studentId);
        records.delete(storedRecord);
    }

    public List<Student> getStudentsByCourse(String courseName) {
        return records.findByCourseIgnoreCase(courseName);
    }
}
