package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.Administrator;
import com.scholario.scholario_demo.exception.administrator.AdministratorFoundException;
import com.scholario.scholario_demo.repository.AdministratorRepository;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

  private final AdministratorRepository administratorRepository;

  public AdministratorService(AdministratorRepository administratorRepository) {
    this.administratorRepository = administratorRepository;
  }

  public List<Administrator> getAllAdministrators(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<Administrator> administratorPage = administratorRepository.findAll(pageable);

    return administratorPage.toList();
  }

  public Administrator getAdministratorById(Long id) throws AdministratorFoundException {
    return administratorRepository.findById(id)
        .orElseThrow(() -> new AdministratorFoundException("Administrator not found."));
  }

  public Administrator createAdministrator(Administrator administrator) {
    String hashedPassword = new BCryptPasswordEncoder().encode(administrator.getPassword());

    administrator.setPassword(hashedPassword);
    return administratorRepository.save(administrator);
  }

  public Administrator updateAdministrator(
      Long id, Administrator administrator) throws AdministratorFoundException {
    Administrator administratorFound = getAdministratorById(id);

    BeanUtils.copyProperties(administrator, administratorFound, "id");

    return administratorRepository.save(administratorFound);
  }

  public void deleteAdministrator(Long id) {
    administratorRepository.deleteById(id);
  }

  // Security

  public UserDetails loadAdministratorByEmail(String email) throws AdministratorFoundException {
    return administratorRepository.findByEmail(email).orElseThrow(
        () -> new AdministratorFoundException("Administrator not found."));
  }
}
