package com.homevalueplus.controller;

import com.homevalueplus.dto.ApiResponse;
import com.homevalueplus.dto.SubmissionDTO;
import com.homevalueplus.dto.SubmissionRequest;
import com.homevalueplus.service.SubmissionService;
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

    // POST /api/submissions - Submit property details (public)
    @PostMapping
    public ResponseEntity<ApiResponse<SubmissionDTO>> createSubmission(
            @Valid @RequestBody SubmissionRequest request) {
        SubmissionDTO created = submissionService.createSubmission(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(created, "Submission created successfully"));
    }

    // GET /api/submissions - Get all submissions (admin only - secured in SecurityConfig)
    @GetMapping
    public ResponseEntity<ApiResponse<List<SubmissionDTO>>> getAllSubmissions() {
        List<SubmissionDTO> submissions = submissionService.getAllSubmissions();
        return ResponseEntity.ok(ApiResponse.success(submissions, "Submissions retrieved successfully"));
    }

    // GET /api/submissions/{id} - Get submission by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SubmissionDTO>> getSubmissionById(@PathVariable Long id) {
        SubmissionDTO submission = submissionService.getSubmissionById(id);
        return ResponseEntity.ok(ApiResponse.success(submission, "Submission retrieved successfully"));
    }
}
