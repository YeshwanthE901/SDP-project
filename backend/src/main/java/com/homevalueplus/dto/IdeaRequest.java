package com.homevalueplus.dto;

import com.homevalueplus.enums.Category;
import com.homevalueplus.enums.PropertyType;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdeaRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Category is required")
    private Category category;

    @NotNull(message = "Estimated cost minimum is required")
    @Positive(message = "Cost must be positive")
    private Double estimatedCostMin;

    @NotNull(message = "Estimated cost maximum is required")
    @Positive(message = "Cost must be positive")
    private Double estimatedCostMax;

    @NotNull(message = "Expected value increase is required")
    @Min(value = 0, message = "Value increase cannot be negative")
    @Max(value = 100, message = "Value increase cannot exceed 100%")
    private Double expectedValueIncrease;

    @NotNull(message = "Property type is required")
    private PropertyType propertyType;
}
