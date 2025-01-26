package dev.senior.bettertech.service;

import dev.senior.bettertech.dto.SubjectDTO;
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

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject createSubject(SubjectDTO subjectDTO) {
        // Map DTO to Entity
        Subject subject = new Subject();
        subject.setName(subjectDTO.getName());

        // Verify and set teacher
        User teacher = userRepository.findById(subjectDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        if (!"ROLE_TEACHER".equals(teacher.getRole())) {
            throw new IllegalArgumentException("User is not a teacher");
        }
        subject.setTeacher(teacher);

        // Verify and set students
        List<User> students = subjectDTO.getStudentIds().stream()
                .map(studentId -> userRepository.findById(studentId)
                        .orElseThrow(() -> new RuntimeException("Student not found")))
                .toList();
        subject.setStudents(students);

        // Save and return the created subject
        return subjectRepository.save(subject);
    }

    public List<Subject> getSubjectsForStudent(Long studentId) {
        // Assuming that Subject entity has a relationship with students
        return subjectRepository.findAllByStudents_Id(studentId);
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found"));
    }
}
