package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.subject.SubjectCreationDto;
import com.scholario.scholario_demo.exception.subject.SubjectNotFoundException;
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

import com.scholario.scholario_demo.dto.subject.SubjectDto;
import com.scholario.scholario_demo.entiity.Subject;
import com.scholario.scholario_demo.service.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
  
  private final SubjectService subjectService;

  @Autowired
  public SubjectController(SubjectService subjectService) {
    this.subjectService = subjectService;
  }

  @GetMapping
  public ResponseEntity<List<SubjectDto>> getAllSubjects(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize
  ) {
    List<Subject> subjects = subjectService.getAllSubjects(pageNumber, pageSize);

    return ResponseEntity.ok(
        subjects.stream()
            .map(SubjectDto::fromEntity)
            .toList()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getSubjectById(@PathVariable  Long id) {
    try {
      SubjectDto subject = SubjectDto.fromEntity(subjectService.getSubjectById(id));
      return ResponseEntity.ok(subject);
    } catch (SubjectNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject with id " +  id + " not found");
    }
  }

  @PostMapping
  public ResponseEntity<SubjectDto> createSubject(@RequestBody  SubjectCreationDto subjectCreationDto) {
    return ResponseEntity.ok(
        SubjectDto.fromEntity(
            subjectService.createSubject(subjectCreationDto.toEntity())
        )
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateSubject(
      @PathVariable  Long id,
      @RequestBody SubjectCreationDto subjectCreationDto) {

    try {
      SubjectDto subject = SubjectDto.fromEntity(subjectService.updateSubject(id, subjectCreationDto.toEntity()));
      return ResponseEntity.ok(subject);
    } catch (SubjectNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject with id " +  id + " not found");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSubject(@PathVariable Long id) {
    subjectService.deleteSubject(id);
    return ResponseEntity.ok("A disciplina de ID : " + id + " foi removida da base de dados");
  }
}
