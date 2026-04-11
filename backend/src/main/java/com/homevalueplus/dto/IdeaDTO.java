package com.homevalueplus.dto;

import com.homevalueplus.enums.Category;
import com.homevalueplus.enums.PropertyType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdeaDTO {

    private Long id;
    private String title;
    private String description;
    private Category category;
    private Double estimatedCostMin;
    private Double estimatedCostMax;
    private Double expectedValueIncrease;
    private PropertyType propertyType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
