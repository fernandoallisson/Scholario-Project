package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.teacher.TeacherCreationDto;
import com.scholario.scholario_demo.dto.teacher.TeacherDto;
import com.scholario.scholario_demo.entiity.Teacher;
import com.scholario.scholario_demo.exception.classes.ClassNotFoundException;
import com.scholario.scholario_demo.exception.teacher.TeacherNotFoundException;
import com.scholario.scholario_demo.service.TeacherService;
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

@RestController
@RequestMapping("/teachers")
public class TeacherController {

  private final TeacherService teacherService;

  @Autowired
  public TeacherController(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  @GetMapping
  public ResponseEntity<List<TeacherDto>> getAllTeachers(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize) {

    List<Teacher> teachers = teacherService.getAllTeachers(pageNumber, pageSize);

    return ResponseEntity.ok(
        teachers.stream()
            .map(TeacherDto::fromEntity)
            .toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getTeacherById(@PathVariable Long id) throws TeacherNotFoundException {
    try {
      TeacherDto teacher = TeacherDto.fromEntity(teacherService.getTeacherById(id));
      return ResponseEntity.ok(teacher);
    } catch (TeacherNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher with id " + id + " not found.");
    }
  }

  @PostMapping
  public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherCreationDto teacherCreationDto) {
    return ResponseEntity.ok(
        TeacherDto.fromEntity(
            teacherService.createTeacher(teacherCreationDto.toEntity())));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('teacher')")
  public ResponseEntity<?> updateTeacher(
      @PathVariable Long id,
      @RequestBody TeacherCreationDto teacherCreationDto) {

    try {
      TeacherDto teacherDto = TeacherDto.fromEntity(teacherService.updateTeacher(id, teacherCreationDto.toEntity()));
      return ResponseEntity.ok(teacherDto);
    } catch (TeacherNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher with id " + id + " not found");
    }
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
    teacherService.deleteTeacher(id);

    return ResponseEntity.ok("Usuário de ID: " + id + " foi removido da base de dados");
  }

  // Relacionar um professor a uma disciplina específica
  // ------------------------------- (N:N)


  @PutMapping("/{teacherId}/subjects/{subjectId}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<TeacherDto> addSubjectToTeacher(
      @PathVariable Long teacherId,
      @PathVariable Long subjectId) {
    return ResponseEntity.ok(
        TeacherDto.fromEntity(
            teacherService.addSubjectToTeacher(teacherId, subjectId)));
  }

  @DeleteMapping("/{teacherId}/subjects/{subjectId}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<TeacherDto> removeSubjectFromTeacher(
      @PathVariable Long teacherId,
      @PathVariable Long subjectId) {
    return ResponseEntity.ok(
        TeacherDto.fromEntity(
            teacherService.removeSubjectFromTeacher(teacherId, subjectId)));
  }

  // Relacionar um professor a uma turma específica
  // ------------------------------- (N:N)

  @PutMapping("/{teacherId}/classes/{classId}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<TeacherDto> addClassToTeacher(
      @PathVariable Long teacherId,
      @PathVariable Long classId) throws ClassNotFoundException, TeacherNotFoundException {
    return ResponseEntity.ok(
        TeacherDto.fromEntity(
            teacherService.addClassToTeacher(teacherId, classId)));
  }

  @DeleteMapping("/{teacherId}/classes/{classId}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<TeacherDto> removeClassFromTeacher(
      @PathVariable Long teacherId,
      @PathVariable Long classId) throws ClassNotFoundException, TeacherNotFoundException {
    return ResponseEntity.ok(
        TeacherDto.fromEntity(
            teacherService.removeClassFromTeacher(teacherId, classId)));
  }

  // Extra methods -----------------------------------------------------
  @GetMapping("/subject/{subjectId}")
  public ResponseEntity<List<TeacherDto>> getTeacherBySubjectId(@PathVariable Long subjectId) {
    List<Teacher> teachers = teacherService.getTeacherBySubjectId(subjectId);

    return ResponseEntity.ok(
        teachers.stream()
            .map(TeacherDto::fromEntity)
            .toList());
  }

  @GetMapping("/search")
  public ResponseEntity<List<TeacherDto>> getTeacherByName(@RequestParam String name) {
    List<Teacher> teacher = teacherService.getTeacherByName(name);

    return ResponseEntity.ok(
        teacher.stream()
            .map(TeacherDto::fromEntity)
            .toList());
  }
}
