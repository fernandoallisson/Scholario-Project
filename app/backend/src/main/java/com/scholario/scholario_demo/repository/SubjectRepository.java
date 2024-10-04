package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
