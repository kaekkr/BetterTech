package dev.senior.bettertech.util;

import dev.senior.bettertech.dto.SubjectDTO;
import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.model.Subject;
import dev.senior.bettertech.model.User;

import java.util.stream.Collectors;

public class SubjectMapper {

    public static SubjectDTO toDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setName(subject.getName());

        // Map only the teacher's ID
        if (subject.getTeacher() != null) {
            dto.setTeacherId(subject.getTeacher().getId());
        }

        // Map student IDs only
        dto.setStudentIds(subject.getStudents().stream()
                .map(User::getId)
                .collect(Collectors.toList()));

        // Map assignment IDs only
        dto.setAssignmentIds(subject.getAssignments().stream()
                .map(Assignment::getId)
                .collect(Collectors.toList()));

        return dto;
    }
}
