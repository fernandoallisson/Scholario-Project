package com.scholario.scholario_demo.service;


import com.scholario.scholario_demo.entiity.Subject;
import com.scholario.scholario_demo.exception.subject.SubjectNotFoundException;
import com.scholario.scholario_demo.repository.SubjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
  private final SubjectRepository subjectRepository;

  @Autowired
  public SubjectService(SubjectRepository subjectRepository) {
    this.subjectRepository = subjectRepository;
  }

  public List<Subject> getAllSubjects(int pageNumber, int pageSize) {

    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<Subject> subjectPage = subjectRepository.findAll(pageable);

    return subjectPage.toList();
  }

  public Subject getSubjectById(Long id) throws SubjectNotFoundException {
    return subjectRepository.findById(id).orElseThrow(
      () -> new SubjectNotFoundException("Subject not found."));
  }

  public Subject createSubject(Subject subject) {
    return subjectRepository.save(subject);
  }

  public Subject updateSubject(Long id, Subject subject) {
    Subject subjectFound = getSubjectById(id);

    subjectFound.setName(subject.getName());
    subjectFound.setDescription(subject.getDescription());
    subjectFound.setTeachers(subject.getTeachers());

    return subjectRepository.save(subjectFound);
  }

  public void deleteSubject(Long id) {
    subjectRepository.deleteById(id);
  }
}
