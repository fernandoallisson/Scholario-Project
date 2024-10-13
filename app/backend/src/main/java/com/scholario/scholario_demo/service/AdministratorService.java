package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.Administrator;
import com.scholario.scholario_demo.exception.administrator.AdministratorNotfoundException;
import com.scholario.scholario_demo.repository.AdministratorRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

  private  final AdministratorRepository administratorRepository;

  public AdministratorService(AdministratorRepository administratorRepository) {
    this.administratorRepository = administratorRepository;
  }

  public List<Administrator> getAllAdministrators(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<Administrator> administratorPage = administratorRepository.findAll(pageable);

    return administratorPage.toList();
  }

  public Administrator getAdministratorById(Long id) throws AdministratorNotfoundException {
    return administratorRepository.findById(id).orElseThrow(() -> new AdministratorNotfoundException("Administrator not found."));
  }

  public Administrator createAdministrator(Administrator administrator) {
    String hashedPassword = new BCryptPasswordEncoder().encode(administrator.getPassword());

    administrator.setPassword(hashedPassword);
    return administratorRepository.save(administrator);
  }

  public Administrator updateAdministrator(
      Long id, Administrator administrator) throws AdministratorNotfoundException {
    Administrator administratorFound = getAdministratorById(id);

    administratorFound.setName(administrator.getName());
    administratorFound.setDepartment(administrator.getDepartment());
    administratorFound.setAddress(administrator.getAddress());
    administratorFound.setEmail(administrator.getEmail());
    administratorFound.setBirthdate(administrator.getBirthdate());
    administratorFound.setPassword(administrator.getPassword());
    administratorFound.setPhone(administrator.getPhone());
    administratorFound.setHireDate(administrator.getHireDate());

    return administratorRepository.save(administratorFound);
  }

  public void deleteAdministrator(Long id) {
    administratorRepository.deleteById(id);
  }

  // Security


  public UserDetails loadAdministratorByEmail(String email) throws AdministratorNotfoundException {
    return administratorRepository.findByEmail(email).orElseThrow(
      () -> new AdministratorNotfoundException("Administrator not found."));
  }
}
