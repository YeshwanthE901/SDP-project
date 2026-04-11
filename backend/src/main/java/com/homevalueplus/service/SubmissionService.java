package com.homevalueplus.service;

import com.homevalueplus.dto.SubmissionDTO;
import com.homevalueplus.dto.SubmissionRequest;
import com.homevalueplus.entity.Submission;
import com.homevalueplus.exception.ResourceNotFoundException;
import com.homevalueplus.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    // Create submission (public)
    @Transactional
    public SubmissionDTO createSubmission(SubmissionRequest request) {
        Submission submission = Submission.builder()
                .ownerName(request.getOwnerName())
                .city(request.getCity())
                .propertyType(request.getPropertyType())
                .propertyCondition(request.getPropertyCondition())
                .budget(request.getBudget())
                .build();

        Submission saved = submissionRepository.save(submission);
        return toDTO(saved);
    }

    // Get all submissions (admin)
    public List<SubmissionDTO> getAllSubmissions() {
        return submissionRepository.findAllByOrderBySubmissionDateDesc()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Get submission by ID
    public SubmissionDTO getSubmissionById(Long id) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Submission", id));
        return toDTO(submission);
    }

    // Delete submission (admin)
    @Transactional
    public void deleteSubmission(Long id) {
        if (!submissionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Submission", id);
        }
        submissionRepository.deleteById(id);
    }

    // Entity to DTO mapper
    private SubmissionDTO toDTO(Submission submission) {
        return SubmissionDTO.builder()
                .id(submission.getId())
                .ownerName(submission.getOwnerName())
                .city(submission.getCity())
                .propertyType(submission.getPropertyType())
                .propertyCondition(submission.getPropertyCondition())
                .budget(submission.getBudget())
                .submissionDate(submission.getSubmissionDate())
                .build();
    }
}
