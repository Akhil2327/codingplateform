package com.codeplateform.coding.repository;

import com.codeplateform.coding.entity.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    // Fetch all submissions by a specific participant ID
    List<Submission> findByParticipantId(String participantId);

    // Fetch all submissions for a specific problem ID
    List<Submission> findByProblemId(String problemId);

    // Paginated submissions for a specific participant ID (useful for large datasets)
    Page<Submission> findByParticipantId(String participantId, Pageable pageable);

    // Fetch submissions with a specific language
    List<Submission> findByLanguage(String language);

    // Custom query: Find submissions by participant ID and problem ID (Optional use case)
    @Query("SELECT s FROM Submission s WHERE s.participantId = :participantId AND s.problemId = :problemId")
    List<Submission> findSubmissionsByParticipantAndProblem(String participantId, String problemId);

    // Custom query: Fetch the latest submissions by a specific participant (sorted by ID)
    @Query("SELECT s FROM Submission s WHERE s.participantId = :participantId ORDER BY s.id DESC")
    List<Submission> findLatestSubmissionsByParticipant(String participantId);
}
