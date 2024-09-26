package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

}
