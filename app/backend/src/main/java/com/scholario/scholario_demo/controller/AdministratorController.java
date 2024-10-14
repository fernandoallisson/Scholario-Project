package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.adminstrator.AdministratorCreationDto;
import com.scholario.scholario_demo.dto.adminstrator.AdministratorDto;
import com.scholario.scholario_demo.entiity.Administrator;
import com.scholario.scholario_demo.exception.administrator.AdministratorFoundException;
import com.scholario.scholario_demo.service.AdministratorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Administrator controller.
 */
@RestController
@RequestMapping("/administrators")
public class AdministratorController {

  private final AdministratorService administratorService;

  /**
   * Instantiates a new Administrator controller.
   *
   * @param administratorService the administrator service
   */
  @Autowired
  public AdministratorController(AdministratorService administratorService) {
    this.administratorService = administratorService;
  }

  /**
   * Gets all administrators.
   *
   * @param pageNumber the page number
   * @param pageSize   the page size
   * @return the all administrators
   */
  @GetMapping
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<List<AdministratorDto>> getAllAdministrators(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize
  ) {
    List<Administrator> administratorList =
        administratorService.getAllAdministrators(pageNumber, pageSize);

    return ResponseEntity.ok(
        administratorList.stream()
            .map(AdministratorDto::fromEntity)
            .toList()
    );
  }

  /**
   * Gets administrator by id.
   *
   * @param id the id
   * @return the administrator by id
   * @throws AdministratorFoundException the administrator notfound exception
   */
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<?> getAdministratorById(@PathVariable Long id)
      throws AdministratorFoundException {
    try {
      AdministratorDto administratorDto =
          AdministratorDto.fromEntity(administratorService.getAdministratorById(id));
      return ResponseEntity.ok(administratorDto);
    } catch (AdministratorFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Administrator with id " +  id + " not found");
    }
  }

  /**
   * Create administrator response entity.
   *
   * @param administratorCreationDto the administrator creation dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<AdministratorDto> createAdministrator(
      @RequestBody AdministratorCreationDto administratorCreationDto
  ) {
    return ResponseEntity.ok(
        AdministratorDto.fromEntity(
            administratorService.createAdministrator(administratorCreationDto.toEntity()))
    );
  }

  /**
   * Update administrator response entity.
   *
   * @param id                       the id
   * @param administratorCreationDto the administrator creation dto
   * @return the response entity
   * @throws AdministratorFoundException the administrator notfound exception
   */
  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<?> updateAdministrator(
      @PathVariable Long id,
      @RequestBody AdministratorCreationDto administratorCreationDto
  ) throws AdministratorFoundException {
    try {
      AdministratorDto administratorDto = AdministratorDto.fromEntity(
          administratorService.updateAdministrator(id, administratorCreationDto.toEntity()));
      return ResponseEntity.ok(administratorDto);
    } catch (AdministratorFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Administrator with id " +  id + " not found");
    }
  }

  /**
   * Delete administrator response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<String> deleteAdministrator(@PathVariable Long id) {
    administratorService.deleteAdministrator(id);
    return ResponseEntity.ok("Administrador de ID: " + id + " foi removido da base de dados");
  }
}
