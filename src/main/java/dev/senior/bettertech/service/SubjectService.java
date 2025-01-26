package dev.senior.bettertech.service;

import dev.senior.bettertech.model.Subject;
import dev.senior.bettertech.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public List<Subject> getSubjectsForStudent(Long studentId) {
        // Assuming that Subject entity has a relationship with students
        return subjectRepository.findAllByStudents_Id(studentId);
    }
}
