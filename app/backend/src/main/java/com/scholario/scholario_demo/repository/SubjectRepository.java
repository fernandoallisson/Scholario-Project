package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Subject;
import com.scholario.scholario_demo.entiity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Subject repository.
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
