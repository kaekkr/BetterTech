package dev.senior.bettertech.service;

import dev.senior.bettertech.model.Role;
import dev.senior.bettertech.model.Subject;
import dev.senior.bettertech.model.User;
import dev.senior.bettertech.repository.SubjectRepository;
import dev.senior.bettertech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void assignTeacher(Long subjectId, Long teacherId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (!teacher.getRole().equals(Role.ROLE_TEACHER)) {
            throw new RuntimeException("User is not a teacher");
        }

        subject.setTeacher(teacher);
        subjectRepository.save(subject);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
