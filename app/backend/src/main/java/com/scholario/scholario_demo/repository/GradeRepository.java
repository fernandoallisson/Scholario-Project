package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Grade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

  List<Grade> findByStudentGradesId(Long studentId);

  List<Grade> findBySubjectGradesId(Long subjectId);

  List<Grade> findByStudentGradesIdAndSubjectGradesId(Long studentId, Long subjectId);
}
