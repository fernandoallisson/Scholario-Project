package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.adminstrator.AdministratorCreationDto;
import com.scholario.scholario_demo.dto.adminstrator.AdministratorDto;
import com.scholario.scholario_demo.entiity.Administrator;
import com.scholario.scholario_demo.exception.administrator.AdministratorNotfoundException;
import com.scholario.scholario_demo.service.AdministratorService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {

  private final AdministratorService administratorService;

  public AdministratorController(AdministratorService administratorService) {
    this.administratorService = administratorService;
  }

  @GetMapping
  public ResponseEntity<List<AdministratorDto>> getAllAdministrators(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize
  ) {
    List<Administrator> administratorList = administratorService.getAllAdministrators(pageNumber, pageSize);

    return ResponseEntity.ok(
        administratorList.stream()
            .map(AdministratorDto::fromEntity)
            .toList()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<AdministratorDto> getAdministratorById(@PathVariable Long id)
  throws AdministratorNotfoundException
  {
    return ResponseEntity.ok(
        AdministratorDto.fromEntity(administratorService.getAdministratorById(id))
    );
  }

  @PostMapping
  public ResponseEntity<AdministratorDto> createAdministrator(
      @RequestBody AdministratorCreationDto administratorCreationDto
  ) {
    return ResponseEntity.ok(
        AdministratorDto.fromEntity(
            administratorService.createAdministrator(administratorCreationDto.toEntity()))
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<AdministratorDto> updateAdministrator(
      @PathVariable Long id, @RequestBody AdministratorCreationDto administratorCreationDto
  ) throws AdministratorNotfoundException {
    return ResponseEntity.ok(
        AdministratorDto.fromEntity(
            administratorService.updateAdministrator(id, administratorCreationDto.toEntity())
        )
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteAdministrator(@PathVariable Long id) {
    administratorService.deleteAdministrator(id);
    return ResponseEntity.ok("Administrador de ID: " + id + " foi removido da base de dados");
  }
}
