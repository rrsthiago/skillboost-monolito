package com.rrsthiago.skillboost.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseActivityDto {

    @Null
    private BigInteger id;

    @NotNull
    private Integer score;

//    @Valid
//    @NotNull
    private CourseActivityTypeDto activityType;

//    @Valid
//    @NotNull
    private ProfessionalDto professional;

}
