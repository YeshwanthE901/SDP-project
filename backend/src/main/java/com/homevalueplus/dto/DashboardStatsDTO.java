package com.homevalueplus.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDTO {

    private long totalIdeas;
    private long totalSubmissions;
    private long totalCategories;
    private Map<String, Long> ideasByCategory;
    private Map<String, Long> submissionsByPropertyType;
    private Map<String, Long> submissionsByCondition;
    private Double averageBudget;
    private long recentSubmissions; // last 7 days
}
