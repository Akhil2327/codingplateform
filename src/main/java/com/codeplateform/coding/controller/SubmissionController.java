package com.codeplateform.coding.controller;

import com.codeplateform.coding.entity.Submission;
import com.codeplateform.coding.service.SubmissionService;
import com.codeplateform.coding.payload.ApiResponse; // Custom response class for better output
import jakarta.validation.Valid; // For validation
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    // Endpoint to submit code
    @PostMapping
    public ResponseEntity<ApiResponse> submitCode(@Valid @RequestBody Submission submission) {
        Submission savedSubmission = submissionService.saveSubmission(submission);
        return ResponseEntity.ok(new ApiResponse(true, "Submission saved successfully", savedSubmission));
    }

    // Global exception handler for unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, "An error occurred: " + ex.getMessage(), null));
    }
}
