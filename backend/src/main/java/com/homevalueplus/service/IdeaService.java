package com.homevalueplus.service;

import com.homevalueplus.dto.IdeaDTO;
import com.homevalueplus.dto.IdeaRequest;
import com.homevalueplus.entity.Idea;
import com.homevalueplus.enums.Category;
import com.homevalueplus.enums.PropertyType;
import com.homevalueplus.exception.ResourceNotFoundException;
import com.homevalueplus.repository.IdeaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    // Get all ideas with pagination and optional filtering
    public Page<IdeaDTO> getAllIdeas(int page, int size, String sortBy, String direction,
                                     Category category, PropertyType propertyType) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Idea> ideas;

        if (category != null && propertyType != null) {
            ideas = ideaRepository.findByCategoryAndPropertyType(category, propertyType, pageable);
        } else if (category != null) {
            ideas = ideaRepository.findByCategory(category, pageable);
        } else if (propertyType != null) {
            ideas = ideaRepository.findByPropertyType(propertyType, pageable);
        } else {
            ideas = ideaRepository.findAll(pageable);
        }

        return ideas.map(this::toDTO);
    }

    // Get idea by ID
    public IdeaDTO getIdeaById(Long id) {
        Idea idea = ideaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Idea", id));
        return toDTO(idea);
    }

    // Create new idea (admin only)
    @Transactional
    public IdeaDTO createIdea(IdeaRequest request) {
        Idea idea = Idea.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .estimatedCostMin(request.getEstimatedCostMin())
                .estimatedCostMax(request.getEstimatedCostMax())
                .expectedValueIncrease(request.getExpectedValueIncrease())
                .propertyType(request.getPropertyType())
                .build();

        Idea saved = ideaRepository.save(idea);
        return toDTO(saved);
    }

    // Update idea (admin only)
    @Transactional
    public IdeaDTO updateIdea(Long id, IdeaRequest request) {
        Idea idea = ideaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Idea", id));

        idea.setTitle(request.getTitle());
        idea.setDescription(request.getDescription());
        idea.setCategory(request.getCategory());
        idea.setEstimatedCostMin(request.getEstimatedCostMin());
        idea.setEstimatedCostMax(request.getEstimatedCostMax());
        idea.setExpectedValueIncrease(request.getExpectedValueIncrease());
        idea.setPropertyType(request.getPropertyType());

        Idea updated = ideaRepository.save(idea);
        return toDTO(updated);
    }

    // Delete idea (admin only)
    @Transactional
    public void deleteIdea(Long id) {
        if (!ideaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Idea", id);
        }
        ideaRepository.deleteById(id);
    }

    // Entity to DTO mapper
    private IdeaDTO toDTO(Idea idea) {
        return IdeaDTO.builder()
                .id(idea.getId())
                .title(idea.getTitle())
                .description(idea.getDescription())
                .category(idea.getCategory())
                .estimatedCostMin(idea.getEstimatedCostMin())
                .estimatedCostMax(idea.getEstimatedCostMax())
                .expectedValueIncrease(idea.getExpectedValueIncrease())
                .propertyType(idea.getPropertyType())
                .createdAt(idea.getCreatedAt())
                .updatedAt(idea.getUpdatedAt())
                .build();
    }
}
