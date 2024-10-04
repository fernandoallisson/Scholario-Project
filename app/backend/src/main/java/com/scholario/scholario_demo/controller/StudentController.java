package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.student.StudentCreationDto;
import com.scholario.scholario_demo.dto.student.StudentDto;
import com.scholario.scholario_demo.entiity.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public ResponseEntity<List<StudentDto>> getAllStudents() {
    List<Student> students = studentService.getAllStudents();

    return ResponseEntity.ok(
        students.stream()
            .map(StudentDto::fromEntity)
            .toList()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
    return ResponseEntity.ok(
        StudentDto.fromEntity(studentService.getStudentById(id))
    );
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
  public ResponseEntity<StudentDto> updateStudent(
      @PathVariable  Long id,
      @RequestBody StudentCreationDto studentCreationDto) {

    return ResponseEntity.ok(
        StudentDto.fromEntity(
            studentService.updateStudent(id, studentCreationDto.toEntity())
        ));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteStudent(Long id) {
    studentService.deleteStudent(id);

    return ResponseEntity.ok("Usuário de ID: " + id + " foi removido da base de dados");
  }
}
