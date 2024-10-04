package com.scholario.scholario_demo.dto.subject;

import com.scholario.scholario_demo.entiity.Subject;

public record subjectDto(
    Long id,
    String name,
    String description
) {

    public static subjectDto fromEntity(Subject subject) {
        return new subjectDto(
            subject.getId(),
            subject.getName(),
            subject.getDescription()
        );
    }

}
