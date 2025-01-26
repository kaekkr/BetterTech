package dev.senior.bettertech.controller;

import dev.senior.bettertech.dto.SubjectDTO;
import dev.senior.bettertech.model.Subject;
import dev.senior.bettertech.service.SubjectService;
import dev.senior.bettertech.util.SubjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public List<SubjectDTO> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return subjects.stream()
                .map(SubjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public SubjectDTO createSubject(@RequestBody SubjectDTO subjectDTO) {
        Subject subject = subjectService.createSubject(subjectDTO);
        return SubjectMapper.toDTO(subject);
    }
}
