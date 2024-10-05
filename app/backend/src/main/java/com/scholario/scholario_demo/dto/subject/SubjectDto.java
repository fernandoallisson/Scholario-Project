package com.scholario.scholario_demo.dto.subject;

import com.scholario.scholario_demo.entiity.Subject;

public record SubjectDto(
    Long id,
    String name,
    String description
) {

    public static SubjectDto fromEntity(Subject subject) {
        return new SubjectDto(
            subject.getId(),
            subject.getName(),
            subject.getDescription()
        );
    }

}
