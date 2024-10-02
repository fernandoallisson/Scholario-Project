package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
