package dev.senior.bettertech.util;

import dev.senior.bettertech.dto.AssignmentDTO;
import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.model.Subject;
import dev.senior.bettertech.model.Submission;

import java.util.List;
import java.util.stream.Collectors;

public class AssignmentMapper {
    public static AssignmentDTO toDTO(Assignment assignment) {
        AssignmentDTO dto = new AssignmentDTO();
        dto.setId(assignment.getId());
        dto.setTitle(assignment.getTitle());
        dto.setDescription(assignment.getDescription());
        dto.setDueDate(assignment.getDueDate());
        dto.setSubjectId(assignment.getSubject().getId()); // Only include subject ID
        // Map submission IDs only
        dto.setSubmissionIds(assignment.getSubmissions().stream()
                .map(Submission::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    public static Assignment toEntity(AssignmentDTO dto) {
        Assignment assignment = new Assignment();
        assignment.setId(dto.getId());
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setDueDate(dto.getDueDate());
        // Subject must be set elsewhere, as it’s not fully loaded in DTO
        if (dto.getSubjectId() != null) {
            Subject subject = new Subject();
            subject.setId(dto.getSubjectId());
            assignment.setSubject(subject);
        }
        return assignment;
    }
}

