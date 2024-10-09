package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.student.StudentCreationDto;
import com.scholario.scholario_demo.dto.student.StudentDto;
import com.scholario.scholario_demo.entiity.Student;

import com.scholario.scholario_demo.exception.classes.ClassNotFoundException;
import com.scholario.scholario_demo.exception.student.StudentNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scholario.scholario_demo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public ResponseEntity<List<StudentDto>> getAllStudents(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize
  ) {
    List<Student> students = studentService.getAllStudents(pageNumber, pageSize);

    return ResponseEntity.ok(
        students.stream()
            .map(StudentDto::fromEntity)
            .toList()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getStudentById(@PathVariable Long id) {
    try {
      StudentDto studentDto = StudentDto.fromEntity(studentService.getStudentById(id));
      return ResponseEntity.ok(studentDto);
    } catch (StudentNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + id + " not found.");
    }
  } 

  @PostMapping
  public ResponseEntity<StudentDto> createStudent(@RequestBody StudentCreationDto studentCreationDto) {
    return ResponseEntity.ok(
        StudentDto.fromEntity(
            studentService.createStudent(studentCreationDto.toEntity())
        )
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateStudent(
      @PathVariable  Long id,
      @RequestBody StudentCreationDto studentCreationDto) {

    try {
      StudentDto studentDto = StudentDto.fromEntity(studentService.updateStudent(id, studentCreationDto.toEntity()));
      return ResponseEntity.ok(studentDto);
    } catch (StudentNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " +  id + " not found");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);

    return ResponseEntity.ok("Usuário de ID: " + id + " foi removido da base de dados");
  }

  // Relacionar um estudante a uma classe -------------- N:N
  @PutMapping("/{studentId}/classes/{classId}")
  public ResponseEntity<?> addClassToStudent(
      @PathVariable Long studentId,
      @PathVariable Long classId) {
    try {
      StudentDto updatedStudent = StudentDto.fromEntity(studentService.addClassToStudent(studentId, classId));
      return ResponseEntity.ok(updatedStudent);
    } catch (StudentNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + studentId + " not found.");
    } catch (ClassNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class with id " + classId + " not found.");
    }
  }

@DeleteMapping("/{studentId}/classes/{classId}")
public ResponseEntity<?> removeClassFromStudent(
    @PathVariable Long studentId,
    @PathVariable Long classId) {
  try {
    StudentDto updatedStudent = StudentDto.fromEntity(studentService.removeClassFromStudent(studentId, classId));
    return ResponseEntity.ok(updatedStudent);
  } catch (StudentNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + studentId + " not found.");
  } catch (ClassNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class with id " + classId + " not found.");
  }
}
}
