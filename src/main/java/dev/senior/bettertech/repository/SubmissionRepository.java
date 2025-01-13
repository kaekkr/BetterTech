package dev.senior.bettertech.repository;

import dev.senior.bettertech.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
