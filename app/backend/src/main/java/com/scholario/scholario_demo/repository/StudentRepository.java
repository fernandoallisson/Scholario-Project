package com.scholario.scholario_demo.repository;
import java.util.List;

import com.scholario.scholario_demo.entiity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  List<Student> findByNameContaining(String name);

  @Query("SELECT s FROM Student s JOIN s.classesStudents c WHERE c.id = :classId")
  List<Student> findByClassId(@Param("classId") Long classId);

}
