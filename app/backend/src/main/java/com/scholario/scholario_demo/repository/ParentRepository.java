package com.scholario.scholario_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scholario.scholario_demo.entiity.Parent;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

}