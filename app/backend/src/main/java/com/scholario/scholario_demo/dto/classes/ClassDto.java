package com.scholario.scholario_demo.dto.classes;

import com.scholario.scholario_demo.dto.subject.SubjectDto;
import com.scholario.scholario_demo.entiity.Classe;
import java.util.List;


/**
 * The type Class dto.
 */
public record ClassDto(Long id, String name, String shift, int year, List<SubjectDto> subjects) {

  /**
   * From entity class dto.
   *
   * @param classes the classes
   * @return the class dto
   */
  public static ClassDto fromEntity(Classe classes) {
    if (classes == null) {
      throw new IllegalArgumentException("Classe must not be null");
    }

    return new ClassDto(
        classes.getId(),
        classes.getName(),
        classes.getShift(),
        classes.getYear(),
        classes.getSubjectClasses()
            .stream()
            .map(SubjectDto::fromEntity)
            .toList()
    );
  }
}
