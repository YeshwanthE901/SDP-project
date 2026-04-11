package com.homevalueplus.dto;

import com.homevalueplus.enums.PropertyCondition;
import com.homevalueplus.enums.PropertyType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionDTO {

    private Long id;
    private String ownerName;
    private String city;
    private PropertyType propertyType;
    private PropertyCondition propertyCondition;
    private Double budget;
    private LocalDateTime submissionDate;
}
