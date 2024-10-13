package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Teacher;
import com.scholario.scholario_demo.entiity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM Teacher t JOIN t.subject s WHERE s.id = :subjectId")
  List<Teacher> findBySubjectId(@Param("subjectId") Long subjectId);

  List<Teacher> findByNameContaining(String name);

  Optional<User> findByEmail(String email);
}
