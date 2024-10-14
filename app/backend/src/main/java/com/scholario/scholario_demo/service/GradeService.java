package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.Grade;
import com.scholario.scholario_demo.entiity.Student;
import com.scholario.scholario_demo.entiity.Subject;
import com.scholario.scholario_demo.exception.grade.GradeNotFoundException;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scholario.scholario_demo.repository.GradeRepository;

@Service
public class GradeService {

  private final GradeRepository gradeRepository;
  private final StudentService studentService;
  private final SubjectService subjectService;

  public GradeService(GradeRepository gradeRepository
      , StudentService studentService
      , SubjectService subjectService) {
    this.gradeRepository = gradeRepository;
    this.studentService = studentService;
    this.subjectService = subjectService;
  }

  public List<Grade> getAllGrades(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<Grade> gradePage = gradeRepository.findAll(pageable);

    return gradePage.toList();
  }

  public Grade getGradeById(Long id) throws GradeNotFoundException {
    return gradeRepository.findById(id).orElseThrow(() -> new GradeNotFoundException("Grade not found"));
  }

  public Grade createGrade(Grade grade, Long studentId, Long subjectId) {
    Subject subject = subjectService.getSubjectById(subjectId);
    Student student = studentService.getStudentById(studentId);

    grade.setGradeValue(grade.getGradeValue());
    grade.setDate(grade.getDate());
    grade.setStudentGrades(student);
    grade.setSubjectGrades(subject);

    return gradeRepository.save(grade);
  }

  public List<Grade> getGradeByStudentId(Long studentId) {
    return gradeRepository.findByStudentGradesId(studentId);
  }

  public List<Grade> getGradeBySubjectId(Long subjectId) {
    return gradeRepository.findBySubjectGradesId(subjectId);
  }

  public List<Grade> getGradeByStudentIdAndSubjectId(Long studentId, Long subjectId) {
    return gradeRepository.findByStudentGradesIdAndSubjectGradesId(studentId, subjectId);
  }

  public Grade updateGrade(Long id, Grade grade) throws GradeNotFoundException {
    Grade gradeFound = gradeRepository.findById(id).orElseThrow(
        () -> new GradeNotFoundException("Grade not found")
    );

    if (gradeFound == null) {
      return null;
    }

      BeanUtils.copyProperties(grade, gradeFound, "id");

    return gradeRepository.save(gradeFound);
  }

  public void deleteGrade(Long id) {
    gradeRepository.deleteById(id);
  }
}
