package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Administrator;
import com.scholario.scholario_demo.entiity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Administrator repository.
 */
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

  /**
   * Find by email optional.
   *
   * @param email the email
   * @return the optional
   */
  Optional<User> findByEmail(String email);
}
