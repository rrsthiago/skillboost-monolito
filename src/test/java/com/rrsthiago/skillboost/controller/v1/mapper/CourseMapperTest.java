package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.CourseDto;
import com.rrsthiago.skillboost.model.Course;
import mock.CourseMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CourseMapperTest {

    @InjectMocks
    private CourseMapper courseMapper;

    @Test
    public void testDtoToModel() {
        CourseDto courseDto = CourseMock.getCourseDto();
        Course course = courseMapper.dtoToModel(courseDto);

        assertEquals(courseDto.getName(), course.getName());
        assertEquals(courseDto.getDescription(), course.getDescription());
        assertEquals(courseDto.getSyllabus(), course.getSyllabus());
        assertEquals(courseDto.getTotalHours(), course.getTotalHours());
        assertEquals(courseDto.getGoalPoint(), course.getGoalPoint());
    }

    @Test
    public void testModelToDto() {
        Course course = CourseMock.getCourse();
        CourseDto courseDto = courseMapper.modelToDto(course);

        assertEquals(course.getId(), courseDto.getId());
        assertEquals(course.getName(), courseDto.getName());
        assertEquals(course.getDescription(), courseDto.getDescription());
        assertEquals(course.getSyllabus(), courseDto.getSyllabus());
        assertEquals(course.getTotalHours(), courseDto.getTotalHours());
        assertEquals(course.getGoalPoint(), courseDto.getGoalPoint());
    }

}
