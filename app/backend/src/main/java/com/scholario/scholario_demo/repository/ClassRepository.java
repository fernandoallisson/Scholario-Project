package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Class repository.
 */
@Repository
public interface ClassRepository extends JpaRepository<Classe, Long> {

}
