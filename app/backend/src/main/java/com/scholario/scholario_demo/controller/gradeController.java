package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.grade.GradeCreationDto;
import com.scholario.scholario_demo.dto.grade.GradeDto;
import com.scholario.scholario_demo.entiity.Grade;
import com.scholario.scholario_demo.exception.grade.GradeNotFoundException;

import java.util.List;

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

import com.scholario.scholario_demo.service.GradeService;

@RestController
@RequestMapping("/grades")
public class gradeController {

  private final GradeService gradeService;

  public gradeController(GradeService gradeService) {
    this.gradeService = gradeService;
  }

  @GetMapping
  public ResponseEntity<List<GradeDto>> getAllGrades(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize) {

    List<Grade> grades = gradeService.getAllGrades(pageNumber, pageSize);

    return ResponseEntity.ok(grades.stream().map(GradeDto::fromEntity).toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getGradeById(@PathVariable Long id) throws GradeNotFoundException {
    try {
      GradeDto gradeDto = GradeDto.fromEntity(gradeService.getGradeById(id));
      return ResponseEntity.ok(gradeDto);
    } catch (GradeNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grade with id " + id + " not found.");
    }
  }

  @PostMapping("/{subjectId}/{studentId}")
  @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
  public ResponseEntity<GradeDto> createGrade(@RequestBody GradeCreationDto gradeCreationDto,
      @PathVariable Long studentId, @PathVariable Long subjectId) {
    GradeDto createdGrade = GradeDto.fromEntity(gradeService.createGrade(gradeCreationDto.toEntity(), studentId, subjectId));

    return ResponseEntity.status(HttpStatus.CREATED).body(createdGrade);
  }

  @GetMapping("/student/{studentId}")
  public ResponseEntity<List<GradeDto>> getGradeByStudentId(@PathVariable Long studentId) {
    List<Grade> grades = gradeService.getGradeByStudentId(studentId);

    return ResponseEntity.ok(grades.stream().map(GradeDto::fromEntity).toList());
  }

  @GetMapping("/subject/{subjectId}")
  public ResponseEntity<List<GradeDto>> getGradeBySubjectId(@PathVariable Long subjectId) {
    List<Grade> grades = gradeService.getGradeBySubjectId(subjectId);

    return ResponseEntity.ok(grades.stream().map(GradeDto::fromEntity).toList());
  }

  @GetMapping("/subject/{subjectId}/student/{studentId}")
  public ResponseEntity<List<GradeDto>> getGradeByStudentIdAndSubjectId(@PathVariable Long studentId,
      @PathVariable Long subjectId) {
    List<Grade> grades = gradeService.getGradeByStudentIdAndSubjectId(studentId, subjectId);

    return ResponseEntity.ok(grades.stream().map(GradeDto::fromEntity).toList());
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public ResponseEntity<?> updateGrade(@PathVariable Long id, @RequestBody GradeCreationDto gradeCreationDto) {
    try {
      GradeDto updatedGrade = GradeDto.fromEntity(gradeService.updateGrade(id, gradeCreationDto.toEntity()));
      return ResponseEntity.ok(updatedGrade);
    } catch (GradeNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grade with id " + id + " not found.");
    }
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<?> deleteGrade(@PathVariable Long id) {
    gradeService.deleteGrade(id);

    return ResponseEntity.ok("Grade with id " + id + " deleted.");
  }
}
