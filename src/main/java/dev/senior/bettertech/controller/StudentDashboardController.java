package dev.senior.bettertech.controller;

import dev.senior.bettertech.model.Assignment;
import dev.senior.bettertech.service.StudentDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/dashboard")
@RequiredArgsConstructor
public class StudentDashboardController {
    private final StudentDashboardService dashboardService;

    @GetMapping
    public ResponseEntity<List<Assignment>> getUpcomingAssignments(@RequestParam Long studentId) {
        return ResponseEntity.ok(dashboardService.getUpcomingAssignments(studentId));
    }
}
