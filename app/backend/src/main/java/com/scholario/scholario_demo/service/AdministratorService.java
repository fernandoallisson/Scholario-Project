package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.repository.AdministratorRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

  private  final AdministratorRepository administratorRepository;

  public AdministratorService(AdministratorRepository administratorRepository) {
    this.administratorRepository = administratorRepository;
  }
  // Construir os serviços para Administradores
}
