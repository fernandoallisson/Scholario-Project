package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
