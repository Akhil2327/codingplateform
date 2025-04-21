package com.codeplateform.coding.service;

import com.codeplateform.coding.entity.Submission;
import com.codeplateform.coding.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    // Save submission and ensure non-null value
    public Submission saveSubmission(Submission submission) {
        if (submission == null) {
            throw new IllegalArgumentException("Submission cannot be null");
        }
        return submissionRepository.save(submission);
    }
}
