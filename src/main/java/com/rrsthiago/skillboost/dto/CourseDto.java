package com.rrsthiago.skillboost.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
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
public class CourseDto {

    @Null
    private BigInteger id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String syllabus;

    @NotNull
    @Positive
    private Integer totalHours;

    @NotNull
    @Positive
    private Integer goalPoint;

}
