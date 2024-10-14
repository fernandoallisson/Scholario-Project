package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.Classe;
import com.scholario.scholario_demo.entiity.Subject;
import com.scholario.scholario_demo.exception.classes.ClassNotFoundException;
import com.scholario.scholario_demo.repository.ClassRepository;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

  private final ClassRepository classRepository;
  private final SubjectService subjectService;

  public ClassService(ClassRepository classRepository, SubjectService subjectService) {
    this.classRepository = classRepository;
    this.subjectService = subjectService;
  }

  public List<Classe> getAllClasses(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<Classe> page = classRepository.findAll(pageable);

    return page.toList();
  }

  public Classe getClassById(Long id) throws ClassNotFoundException{
    return classRepository.findById(id)
        .orElseThrow(() -> new ClassNotFoundException("Class not found."));
  }

  public Classe createClass(Classe classe) {
    return classRepository.save(classe);
  }

  public Classe updateClass(Long id, Classe classe) throws ClassNotFoundException {
    Classe existingClass = classRepository.findById(id)
        .orElseThrow(() -> new ClassNotFoundException("Class not found."));

        BeanUtils.copyProperties(classe, existingClass, "id");

    return classRepository.save(existingClass);
  }

  public void deleteClass(Long id) {
    classRepository.deleteById(id);
  }

  // Associar uma disciplina a uma turma

  public Classe addSubjectToClass(Long classId, Long subjectId) throws ClassNotFoundException {
    Classe classe = getClassById(classId);
    Subject subject = subjectService.getSubjectById(subjectId);

    if(classe.getSubjectClasses().contains(subject)) {
      return classe;
    }

    classe.getSubjectClasses().add(subject);

    return classRepository.save(classe);
  }

  public Classe removeSubjectFromClass(Long classId, Long subjectId) throws ClassNotFoundException {
    Classe classe = getClassById(subjectId);
    Subject subject = subjectService.getSubjectById(subjectId);


    classe.getSubjectClasses().remove(subject);

    return classRepository.save(classe);
  }
}
