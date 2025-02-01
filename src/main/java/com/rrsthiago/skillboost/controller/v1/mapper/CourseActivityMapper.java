package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.CourseActivityDto;
import com.rrsthiago.skillboost.model.CourseActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseActivityMapper {

    @Autowired
    private ProfessionalMapper professionalMapper;

    @Autowired
    private CourseActivityTypeMapper courseActivityTypeMapper;

    public CourseActivity dtoToModel(CourseActivityDto courseActivityDto) {
        return CourseActivity.builder()
                .id(courseActivityDto.getId())
                .score(courseActivityDto.getScore())
                .professional(professionalMapper.idDtoToModel(courseActivityDto.getProfessionalId()))
                .activityType(courseActivityTypeMapper.idDtoToModel(courseActivityDto.getActivityTypeId()))
                .build();
    }

    public CourseActivityDto modelToDto(CourseActivity courseActivity) {
        return CourseActivityDto.builder()
                .id(courseActivity.getId())
                .score(courseActivity.getScore())
                .professional(professionalMapper.modelToDto(courseActivity.getProfessional()))
                .activityType(courseActivityTypeMapper.modelToDto(courseActivity.getActivityType()))
                .build();
    }

}
