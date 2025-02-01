package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.CourseActivityTypeDto;
import com.rrsthiago.skillboost.model.CourseActivityType;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class CourseActivityTypeMapper {

    public CourseActivityType dtoToModel(CourseActivityTypeDto courseActivityTypeDto) {
        return CourseActivityType.builder()
                .id(courseActivityTypeDto.getId())
                .type(courseActivityTypeDto.getType())
                .build();
    }

    public CourseActivityTypeDto modelToDto(CourseActivityType courseActivityType) {
        return CourseActivityTypeDto.builder()
                .id(courseActivityType.getId())
                .type(courseActivityType.getType())
                .build();
    }

    public CourseActivityType idDtoToModel(BigInteger courseActivityTypeId) {
        return CourseActivityType.builder()
                .id(courseActivityTypeId)
                .build();
    }

}
