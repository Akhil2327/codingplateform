package com.codeplateform.coding.controller;

import com.codeplateform.coding.entity.Submission;
import com.codeplateform.coding.payload.ApiResponse;
import com.codeplateform.coding.service.SubmissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    // 🔹 Single submission
    @PostMapping
    public ResponseEntity<ApiResponse> submitCode(@Valid @RequestBody Submission submission) {
        Submission savedSubmission = submissionService.saveSubmission(submission);
        return ResponseEntity.ok(new ApiResponse(true, "Submission saved successfully", savedSubmission));
    }

    // 🔸 Multiple submissions at once
    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse> submitMultipleCodes(@Valid @RequestBody List<Submission> submissions) {
        List<Submission> savedSubmissions = submissionService.saveAllSubmissions(submissions);
        return ResponseEntity.ok(new ApiResponse(true, "All submissions saved successfully", savedSubmissions));
    }

    // 🌐 Global exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, "An error occurred: " + ex.getMessage(), null));
    }
}
