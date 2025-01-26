package dev.senior.bettertech.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SubmissionDTO {
    private Long id;
    private Long assignmentId; // Optional for context
    private String studentName;
    private String content;
    private boolean approved;
    private LocalDateTime submissionDate;
    private String feedback;
}
