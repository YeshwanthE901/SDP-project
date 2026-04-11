package com.homevalueplus.dto;

import com.homevalueplus.enums.PropertyCondition;
import com.homevalueplus.enums.PropertyType;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionRequest {

    @NotBlank(message = "Owner name is required")
    private String ownerName;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "Property type is required")
    private PropertyType propertyType;

    @NotNull(message = "Property condition is required")
    private PropertyCondition propertyCondition;

    @NotNull(message = "Budget is required")
    @Positive(message = "Budget must be positive")
    private Double budget;
}
