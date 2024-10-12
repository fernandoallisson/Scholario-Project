package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.classes.ClassCreationDto;
import com.scholario.scholario_demo.dto.classes.ClassDto;
import com.scholario.scholario_demo.entiity.Classe;
import com.scholario.scholario_demo.exception.classes.ClassNotFoundException;
import com.scholario.scholario_demo.service.ClassService;
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

@RestController
@RequestMapping("/classes")
public class ClassController {

  private final ClassService classService;

  @Autowired
  public ClassController(ClassService classService) {
    this.classService = classService;
  }

  @GetMapping
  public ResponseEntity<List<ClassDto>> getAllClasses(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize
  ) {

    List<Classe> classes = classService.getAllClasses(pageNumber, pageSize);

    return ResponseEntity.ok(
        classes.stream()
            .map(ClassDto::fromEntity)
            .toList()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getClassById(@PathVariable Long id) {
    try {
      ClassDto classDto = ClassDto.fromEntity(classService.getClassById(id));
      return ResponseEntity.ok(classDto);
    } catch (ClassNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class with id " +  id + " not found");
    }
  }

  @PostMapping
  public ResponseEntity<ClassDto> createClass(@RequestBody ClassCreationDto
      classCreationDto) {
    return ResponseEntity.ok(
        ClassDto.fromEntity(
            classService.createClass(classCreationDto.toEntity())
        )
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateClass(@PathVariable Long id, @RequestBody ClassCreationDto
      classCreationDto) {
    try {
      ClassDto classe = ClassDto.fromEntity(classService.updateClass(id, classCreationDto.toEntity()));
      return ResponseEntity.ok(classe);
    } catch (ClassNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class with id " +  id + " not found");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteClass(@PathVariable Long id) {
    classService.deleteClass(id);
    return ResponseEntity.ok("Turma de ID: " + id + " foi removido da base de dados");
  }
}
