package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.CourseActivityDto;
import com.rrsthiago.skillboost.dto.CourseActivityTypeDto;
import com.rrsthiago.skillboost.model.CourseActivity;
import com.rrsthiago.skillboost.model.CourseActivityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseActivityMapper {

    @Autowired
    private ProfessionalMapper professionalMapper;

    public CourseActivity dtoToModel(CourseActivityDto courseActivityDto) {
        return CourseActivity.builder()
                .id(courseActivityDto.getId())
                .score(courseActivityDto.getScore())
                .professional(professionalMapper.dtoToModel(courseActivityDto.getProfessional()))
                .activityType(dtoToModel(courseActivityDto.getActivityType()))
                .build();
    }

    private CourseActivityType dtoToModel(CourseActivityTypeDto courseActivityTypeDto) {
        return CourseActivityType.builder()
                .id(courseActivityTypeDto.getId())
                .type(courseActivityTypeDto.getType())
                .build();
    }

    public CourseActivityDto modelToDto(CourseActivity courseActivity) {
        return CourseActivityDto.builder()
                .id(courseActivity.getId())
                .score(courseActivity.getScore())
                .professional(professionalMapper.modelToDto(courseActivity.getProfessional()))
                .activityType(modelToDto(courseActivity.getActivityType()))
                .build();
    }

    private CourseActivityTypeDto modelToDto(CourseActivityType courseActivityType) {
        return CourseActivityTypeDto.builder()
                .id(courseActivityType.getId())
                .type(courseActivityType.getType())
                .build();
    }

}
