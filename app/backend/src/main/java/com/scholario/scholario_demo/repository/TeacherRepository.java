package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Teacher;
import com.scholario.scholario_demo.entiity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Teacher repository.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  /**
   * Find by subject id list.
   *
   * @param subjectId the subject id
   * @return the list
   */
  @Query("SELECT t FROM Teacher t JOIN t.subject s WHERE s.id = :subjectId")
  List<Teacher> findBySubjectId(@Param("subjectId") Long subjectId);

  /**
   * Find by name containing list.
   *
   * @param name the name
   * @return the list
   */
  List<Teacher> findByNameContaining(String name);

  /**
   * Find by email optional.
   *
   * @param email the email
   * @return the optional
   */
  Optional<User> findByEmail(String email);
}
