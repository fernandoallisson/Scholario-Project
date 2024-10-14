package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.student.StudentCreationDto;
import com.scholario.scholario_demo.dto.student.StudentDto;
import com.scholario.scholario_demo.entiity.Student;
import com.scholario.scholario_demo.exception.classes.ClassNotFoundException;
import com.scholario.scholario_demo.exception.student.StudentNotFoundException;
import com.scholario.scholario_demo.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Student controller.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  /**
   * Instantiates a new Student controller.
   *
   * @param studentService the student service
   */
  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  /**
   * Gets all students.
   *
   * @param pageNumber the page number
   * @param pageSize   the page size
   * @return the all students
   */
  @GetMapping
  public ResponseEntity<List<StudentDto>> getAllStudents(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize) {
    List<Student> students = studentService.getAllStudents(pageNumber, pageSize);

    return ResponseEntity.ok(
        students.stream()
            .map(StudentDto::fromEntity)
            .toList());
  }

  /**
   * Gets student by id.
   *
   * @param id the id
   * @return the student by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getStudentById(@PathVariable Long id) {
    try {
      StudentDto studentDto = StudentDto.fromEntity(studentService.getStudentById(id));
      return ResponseEntity.ok(studentDto);
    } catch (StudentNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Student with id " + id + " not found.");
    }
  }

  /**
   * Create student response entity.
   *
   * @param studentCreationDto the student creation dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<StudentDto> createStudent(
      @RequestBody StudentCreationDto studentCreationDto) {
    return ResponseEntity.ok(
        StudentDto.fromEntity(
            studentService.createStudent(studentCreationDto.toEntity())));
  }

  /**
   * Update student response entity.
   *
   * @param id                 the id
   * @param studentCreationDto the student creation dto
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> updateStudent(
      @PathVariable Long id,
      @RequestBody StudentCreationDto studentCreationDto) {

    try {
      StudentDto studentDto =
          StudentDto.fromEntity(studentService.updateStudent(id, studentCreationDto.toEntity()));
      return ResponseEntity.ok(studentDto);
    } catch (StudentNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Student with id " + id + " not found");
    }
  }

  /**
   * Delete student response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);

    return ResponseEntity.ok("Usuário de ID: " + id + " foi removido da base de dados");
  }

  /**
   * Add class to student response entity.
   *
   * @param studentId the student id
   * @param classId   the class id
   * @return the response entity
   */
  // Relacionar um estudante a uma classe -------------- N:N
  @PutMapping("/{studentId}/classes/{classId}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<?> addClassToStudent(
      @PathVariable Long studentId,
      @PathVariable Long classId) {
    try {
      StudentDto updatedStudent =
          StudentDto.fromEntity(studentService.addClassToStudent(studentId, classId));
      return ResponseEntity.ok(updatedStudent);
    } catch (StudentNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Student with id " + studentId + " not found.");
    } catch (ClassNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Class with id " + classId + " not found.");
    }
  }

  /**
   * Remove class from student response entity.
   *
   * @param studentId the student id
   * @param classId   the class id
   * @return the response entity
   */
  @DeleteMapping("/{studentId}/classes/{classId}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<?> removeClassFromStudent(
      @PathVariable Long studentId,
      @PathVariable Long classId) {
    try {
      StudentDto updatedStudent =
          StudentDto.fromEntity(studentService.removeClassFromStudent(studentId, classId));
      return ResponseEntity.ok(updatedStudent);
    } catch (StudentNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Student with id " + studentId + " not found.");
    } catch (ClassNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Class with id " + classId + " not found.");
    }
  }

  // Extra methods -----------------------------------------------------

  /**
   * Gets students by name.
   *
   * @param name the name
   * @return the students by name
   */
  @GetMapping("/search")
  public ResponseEntity<List<StudentDto>> getStudentsByName(@RequestParam String name) {
    List<Student> students = studentService.getStudentsByName(name);

    return ResponseEntity.ok(
        students.stream()
            .map(StudentDto::fromEntity)
            .toList());
  }

  /**
   * Gets student by class id.
   *
   * @param classId the class id
   * @return the student by class id
   */
  @GetMapping("/class/{classId}")
  public ResponseEntity<List<StudentDto>> getStudentByClassId(@PathVariable Long classId) {
    List<Student> students = studentService.getStudentByClassId(classId);

    return ResponseEntity.ok(
        students.stream()
            .map(StudentDto::fromEntity)
            .toList());
  }

}
