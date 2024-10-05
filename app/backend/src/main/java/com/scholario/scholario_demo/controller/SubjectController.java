package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.subject.SubjectCreationDto;
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

import com.scholario.scholario_demo.dto.subject.subjectDto;
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
  public ResponseEntity<List<subjectDto>> getAllSubjects() {
    List<Subject> subjects = subjectService.getAllSubjects();

    return ResponseEntity.ok(
        subjects.stream()
            .map(subjectDto::fromEntity)
            .toList()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<subjectDto> getSubjectById(Long id) {
    return ResponseEntity.ok(
        subjectDto.fromEntity(subjectService.getSubjectById(id))
    );
  }

  @PostMapping
  public ResponseEntity<subjectDto> createSubject(@RequestBody  SubjectCreationDto subjectCreationDto) {
    return ResponseEntity.ok(
        subjectDto.fromEntity(
            subjectService.createSubject(subjectCreationDto.toEntity())
        )
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<subjectDto> updateSubject(
      @PathVariable  Long id,
      @RequestBody SubjectCreationDto subjectCreationDto) {
    return ResponseEntity.ok(
        subjectDto.fromEntity(
            subjectService.updateSubject(id, subjectCreationDto.toEntity())
        )
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSubject(@PathVariable Long id) {
    subjectService.deleteSubject(id);
    return ResponseEntity.ok("A disciplina de ID : " + id + " foi removida da base de dados");
  }
}
