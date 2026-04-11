package com.homevalueplus.service;

import com.homevalueplus.dto.DashboardStatsDTO;
import com.homevalueplus.enums.Category;
import com.homevalueplus.repository.IdeaRepository;
import com.homevalueplus.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final IdeaRepository ideaRepository;
    private final SubmissionRepository submissionRepository;

    public DashboardService(IdeaRepository ideaRepository, SubmissionRepository submissionRepository) {
        this.ideaRepository = ideaRepository;
        this.submissionRepository = submissionRepository;
    }

    public DashboardStatsDTO getDashboardStats() {
        long totalIdeas = ideaRepository.count();
        long totalSubmissions = submissionRepository.count();

        // Count categories that have at least one idea
        long totalCategories = ideaRepository.countByCategories().size();

        // Ideas by category
        Map<String, Long> ideasByCategory = new LinkedHashMap<>();
        for (Category cat : Category.values()) {
            ideasByCategory.put(cat.name(), ideaRepository.countByCategory(cat));
        }

        // Submissions by property type
        Map<String, Long> submissionsByPropertyType = new LinkedHashMap<>();
        List<Object[]> propTypeCounts = submissionRepository.countByPropertyTypes();
        for (Object[] row : propTypeCounts) {
            submissionsByPropertyType.put(row[0].toString(), (Long) row[1]);
        }

        // Submissions by condition
        Map<String, Long> submissionsByCondition = new LinkedHashMap<>();
        List<Object[]> condCounts = submissionRepository.countByConditions();
        for (Object[] row : condCounts) {
            submissionsByCondition.put(row[0].toString(), (Long) row[1]);
        }

        // Average budget
        Double avgBudget = submissionRepository.averageBudget();
        if (avgBudget == null) avgBudget = 0.0;

        // Recent submissions (last 7 days)
        long recentSubmissions = submissionRepository.countBySubmissionDateAfter(
                LocalDateTime.now().minusDays(7));

        return DashboardStatsDTO.builder()
                .totalIdeas(totalIdeas)
                .totalSubmissions(totalSubmissions)
                .totalCategories(totalCategories)
                .ideasByCategory(ideasByCategory)
                .submissionsByPropertyType(submissionsByPropertyType)
                .submissionsByCondition(submissionsByCondition)
                .averageBudget(avgBudget)
                .recentSubmissions(recentSubmissions)
                .build();
    }
}
