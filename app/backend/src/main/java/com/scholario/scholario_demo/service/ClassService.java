package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.Classe;
import com.scholario.scholario_demo.exception.classes.ClassNotFoundException;
import com.scholario.scholario_demo.repository.ClassRepository;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

  private final ClassRepository classRepository;

  public ClassService(ClassRepository classRepository) {
    this.classRepository = classRepository;
  }

  public List<Classe> getAllClasses(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return classRepository.findAll(pageable).toList();
  }

  public com.scholario.scholario_demo.entiity.Classe getClassById(Long id) throws ClassNotFoundException{
    return classRepository.findById(id)
        .orElseThrow(() -> new ClassNotFoundException("Class not found."));
  }

  public Classe createClass(Classe classe) {
    return classRepository.save(classe);
  }

  public Classe updateClass(Long id, Classe classe) throws ClassNotFoundException {
    Classe existingClass = classRepository.findById(id)
        .orElseThrow(() -> new ClassNotFoundException("Class not found."));

    return classRepository.save(existingClass);
  }

  public void deleteClass(Long id) {
    classRepository.deleteById(id);
  }

}
