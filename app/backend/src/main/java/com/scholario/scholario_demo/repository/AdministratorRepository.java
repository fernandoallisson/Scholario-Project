package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}
