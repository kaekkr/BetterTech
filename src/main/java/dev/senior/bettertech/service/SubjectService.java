package dev.senior.bettertech.service;

import dev.senior.bettertech.model.Subject;
import dev.senior.bettertech.model.User;
import dev.senior.bettertech.repository.SubjectRepository;
import dev.senior.bettertech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public Subject createSubject(Subject subjectRequest) {
        // Verify teacher
        User teacher = userRepository.findById(subjectRequest.getTeacher().getId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        if (!teacher.getRole().name().equals("ROLE_TEACHER")) {
            throw new IllegalArgumentException("User is not a teacher");
        }

        // Verify students
        List<User> students = subjectRequest.getStudents().stream()
                .map(student -> userRepository.findById(student.getId())
                        .orElseThrow(() -> new RuntimeException("Student not found")))
                .toList();

        // Set verified teacher and students
        subjectRequest.setTeacher(teacher);
        subjectRequest.setStudents(students);

        return subjectRepository.save(subjectRequest);
    }

    public List<Subject> getSubjectsForStudent(Long studentId) {
        // Assuming that Subject entity has a relationship with students
        return subjectRepository.findAllByStudents_Id(studentId);
    }
}
