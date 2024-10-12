package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}
