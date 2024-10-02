package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
