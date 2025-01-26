package dev.senior.bettertech.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectDTO {
    private Long id;
    private String name;
    private Long teacherId;
    private List<Long> studentIds;
    private List<Long> assignmentIds;
}

