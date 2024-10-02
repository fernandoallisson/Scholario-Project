package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
