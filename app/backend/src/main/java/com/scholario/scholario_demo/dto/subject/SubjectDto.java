package com.scholario.scholario_demo.dto.subject;

import com.scholario.scholario_demo.entiity.Subject;

/**
 * The type Subject dto.
 */
public record SubjectDto(
    Long id,
    String name,
    String description
) {

  /**
  * From entity subject dto.
  *
  * @param subject the subject
  * @return the subject dto
  */
  public static SubjectDto fromEntity(Subject subject) {
    return new SubjectDto(
        subject.getId(),
        subject.getName(),
        subject.getDescription()
    );
  }

}
