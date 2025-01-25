package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.CourseDto;
import com.rrsthiago.skillboost.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course dtoToModel (CourseDto courseDto) {
        return Course.builder()
                .name(courseDto.getName())
                .description(courseDto.getDescription())
                .syllabus(courseDto.getSyllabus())
                .totalHours(courseDto.getTotalHours())
                .goalPoint(courseDto.getGoalPoint())
                .build();
    }

    public CourseDto modelToDto (Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .syllabus(course.getSyllabus())
                .totalHours(course.getTotalHours())
                .goalPoint(course.getGoalPoint())
                .build();
    }

}
