package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Student;
import com.scholario.scholario_demo.entiity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Student repository.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  /**
   * Find by name containing list.
   *
   * @param name the name
   * @return the list
   */
  List<Student> findByNameContaining(String name);

  /**
   * Find by class id list.
   *
   * @param classId the class id
   * @return the list
   */
  @Query("SELECT s FROM Student s JOIN s.classesStudents c WHERE c.id = :classId")
  List<Student> findByClassId(@Param("classId") Long classId);

  /**
   * Find by email optional.
   *
   * @param email the email
   * @return the optional
   */
  Optional<User> findByEmail(String email);

}
