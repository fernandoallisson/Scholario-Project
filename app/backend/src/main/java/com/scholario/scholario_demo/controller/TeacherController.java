package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.teacher.TeacherCreationDto;
import com.scholario.scholario_demo.dto.teacher.TeacherDto;
import com.scholario.scholario_demo.entiity.Teacher;
import com.scholario.scholario_demo.service.TeacherService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

  private final TeacherService teacherService;


  public TeacherController(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  @GetMapping
  public ResponseEntity<List<TeacherDto>> getAllTeachers() {
    List<Teacher> teachers = teacherService.getAllTeachers();

    return ResponseEntity.ok(
        teachers.stream()
            .map(TeacherDto::fromEntity)
            .toList()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
    return ResponseEntity.ok(
        TeacherDto.fromEntity(teacherService.getTeacherById(id))
    );
  }

  @PostMapping
  public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherCreationDto
      teacherCreationDto) {
    return ResponseEntity.ok(
        TeacherDto.fromEntity(
            teacherService.createTeacher(teacherCreationDto.toEntity())
        )
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<TeacherDto> updateTeacher(
      @PathVariable  Long id,
      @RequestBody TeacherCreationDto teacherCreationDto) {

    return ResponseEntity.ok(
        TeacherDto.fromEntity(
            teacherService.updateTeacher(id, teacherCreationDto.toEntity())
        ));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteTeacher(Long id) {
    teacherService.deleteTeacher(id);

    return ResponseEntity.ok("Usuário de ID: " + id + " foi removido da base de dados");
  }

  // Relacionar um professor a uma disciplina específica ------------------------------- (N:N)

  @PutMapping("/{teacherId}/subjects/{subjectId}")
  public ResponseEntity<TeacherDto> addSubjectToTeacher(
      @PathVariable Long teacherId,
      @PathVariable Long subjectId) {
    return ResponseEntity.ok(
        TeacherDto.fromEntity(
            teacherService.addSubjectToTeacher(teacherId, subjectId)
        )
    );
  }

  @DeleteMapping("/{teacherId}/subjects/{subjectId}")
  public ResponseEntity<TeacherDto> removeSubjectFromTeacher(
      @PathVariable Long teacherId,
      @PathVariable Long subjectId) {
    return ResponseEntity.ok(
        TeacherDto.fromEntity(
            teacherService.removeSubjectFromTeacher(teacherId, subjectId)
        )
    );
  }
}
