package com.rrsthiago.skillboost.controller.v1;

import com.rrsthiago.skillboost.controller.v1.mapper.CourseMapper;
import com.rrsthiago.skillboost.dto.CourseDto;
import com.rrsthiago.skillboost.model.Course;
import com.rrsthiago.skillboost.service.CourseService;
import mock.CourseMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {

    @InjectMocks
    private CourseController courseController;

    @Mock
    private CourseService courseService;

    @Spy
    private CourseMapper courseMapper;

    @Test
    public void testList() {
        List<Course> courses = List.of(CourseMock.getCourse());
        when(courseService.list()).thenReturn(courses);

        ResponseEntity<?> response = courseController.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courses, response.getBody());
        verify(courseService, times(1)).list();
    }

    @Test
    public void testGet() {
        Course course = CourseMock.getCourse();
        when(courseService.get(any(BigInteger.class))).thenReturn(course);

        ResponseEntity<?> response = courseController.get(BigInteger.ONE);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(course, response.getBody());
        verify(courseService, times(1)).get(BigInteger.ONE);
    }

    @Test
    public void testCreate() {
        CourseDto courseDto = CourseMock.getCourseDto();
        Course course = CourseMock.getCourse();
        when(courseService.create(any(Course.class))).thenReturn(course);

        ResponseEntity<?> response = courseController.create(courseDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseDto, response.getBody());
        verify(courseService, times(1)).create(any(Course.class));
        verify(courseMapper, times(1)).dtoToModel(any(CourseDto.class));
        verify(courseMapper, times(1)).modelToDto(any(Course.class));
    }

    @Test
    public void testUpdate() {
        CourseDto courseDto = CourseMock.getCourseDto();
        Course course = CourseMock.getCourse();
        when(courseService.update(any(BigInteger.class), any(Course.class))).thenReturn(course);

        ResponseEntity<?> response = courseController.update(BigInteger.ONE, courseDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseDto, response.getBody());
        verify(courseService, times(1)).update(any(BigInteger.class), any(Course.class));
        verify(courseMapper, times(1)).dtoToModel(any(CourseDto.class));
        verify(courseMapper, times(1)).modelToDto(any(Course.class));
    }

    @Test
    public void testDelete() {
        doNothing().when(courseService).delete(any(BigInteger.class));

        ResponseEntity<?> response = courseController.delete(BigInteger.ONE);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(courseService, times(1)).delete(BigInteger.ONE);
    }

}
