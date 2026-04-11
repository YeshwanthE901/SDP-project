package com.homevalueplus.controller;

import com.homevalueplus.dto.ApiResponse;
import com.homevalueplus.dto.IdeaDTO;
import com.homevalueplus.dto.IdeaRequest;
import com.homevalueplus.enums.Category;
import com.homevalueplus.enums.PropertyType;
import com.homevalueplus.service.IdeaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ideas")
public class IdeaController {

    private final IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    // GET /api/ideas - Get all ideas with pagination, sorting, and filtering
    @GetMapping
    public ResponseEntity<ApiResponse<Page<IdeaDTO>>> getAllIdeas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) PropertyType propertyType) {

        Page<IdeaDTO> ideas = ideaService.getAllIdeas(page, size, sortBy, direction, category, propertyType);
        return ResponseEntity.ok(ApiResponse.success(ideas, "Ideas retrieved successfully"));
    }

    // GET /api/ideas/{id} - Get idea by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IdeaDTO>> getIdeaById(@PathVariable Long id) {
        IdeaDTO idea = ideaService.getIdeaById(id);
        return ResponseEntity.ok(ApiResponse.success(idea, "Idea retrieved successfully"));
    }

    // POST /api/ideas - Create new idea (admin only)
    @PostMapping
    public ResponseEntity<ApiResponse<IdeaDTO>> createIdea(@Valid @RequestBody IdeaRequest request) {
        IdeaDTO created = ideaService.createIdea(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(created, "Idea created successfully"));
    }

    // PUT /api/ideas/{id} - Update idea (admin only)
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IdeaDTO>> updateIdea(@PathVariable Long id,
                                                            @Valid @RequestBody IdeaRequest request) {
        IdeaDTO updated = ideaService.updateIdea(id, request);
        return ResponseEntity.ok(ApiResponse.success(updated, "Idea updated successfully"));
    }

    // DELETE /api/ideas/{id} - Delete idea (admin only)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteIdea(@PathVariable Long id) {
        ideaService.deleteIdea(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Idea deleted successfully"));
    }
}
