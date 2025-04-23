package com.codeplateform.coding.service;

import com.codeplateform.coding.entity.Submission;
import com.codeplateform.coding.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    // 🔹 Save a single submission
    public Submission saveSubmission(Submission submission) {
        if (submission == null) {
            throw new IllegalArgumentException("Submission cannot be null");
        }
        return submissionRepository.save(submission);
    }

    // 🔸 Save multiple submissions
    public List<Submission> saveAllSubmissions(List<Submission> submissions) {
        if (submissions == null || submissions.isEmpty()) {
            throw new IllegalArgumentException("Submissions list cannot be null or empty");
        }
        return submissionRepository.saveAll(submissions);
    }
}
