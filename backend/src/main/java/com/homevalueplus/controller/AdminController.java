package com.homevalueplus.controller;

import com.homevalueplus.dto.*;
import com.homevalueplus.service.AdminService;
import com.homevalueplus.service.DashboardService;
import com.homevalueplus.service.SubmissionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final DashboardService dashboardService;
    private final SubmissionService submissionService;

    public AdminController(AdminService adminService, DashboardService dashboardService,
                          SubmissionService submissionService) {
        this.adminService = adminService;
        this.dashboardService = dashboardService;
        this.submissionService = submissionService;
    }

    // POST /api/admin/login - Admin login (public)
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AdminLoginResponse>> login(
            @Valid @RequestBody AdminLoginRequest request) {
        AdminLoginResponse response = adminService.login(request);
        return ResponseEntity.ok(ApiResponse.success(response, "Login successful"));
    }

    // GET /api/admin/dashboard - Get dashboard stats (admin only)
    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<DashboardStatsDTO>> getDashboardStats() {
        DashboardStatsDTO stats = dashboardService.getDashboardStats();
        return ResponseEntity.ok(ApiResponse.success(stats, "Dashboard stats retrieved"));
    }

    // GET /api/admin/submissions - View all submissions (admin only)
    @GetMapping("/submissions")
    public ResponseEntity<ApiResponse<List<SubmissionDTO>>> getSubmissions() {
        List<SubmissionDTO> submissions = submissionService.getAllSubmissions();
        return ResponseEntity.ok(ApiResponse.success(submissions, "Submissions retrieved"));
    }

    // DELETE /api/admin/submissions/{id} - Delete submission (admin only)
    @DeleteMapping("/submissions/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSubmission(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Submission deleted successfully"));
    }
}
