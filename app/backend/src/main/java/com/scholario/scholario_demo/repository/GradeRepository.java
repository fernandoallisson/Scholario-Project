package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Grade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Grade repository.
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

  /**
   * Find by student grades id list.
   *
   * @param studentId the student id
   * @return the list
   */
  List<Grade> findByStudentGradesId(Long studentId);

  /**
   * Find by subject grades id list.
   *
   * @param subjectId the subject id
   * @return the list
   */
  List<Grade> findBySubjectGradesId(Long subjectId);

  /**
   * Find by student grades id and subject grades id list.
   *
   * @param studentId the student id
   * @param subjectId the subject id
   * @return the list
   */
  List<Grade> findByStudentGradesIdAndSubjectGradesId(Long studentId, Long subjectId);
}
