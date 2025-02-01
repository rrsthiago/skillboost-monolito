package com.rrsthiago.skillboost.service;

import com.rrsthiago.skillboost.exception.ResourceNotFoundException;
import com.rrsthiago.skillboost.model.Course;
import com.rrsthiago.skillboost.repository.CourseRepository;
import mock.CourseMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    private Course course;

    @BeforeEach
    void setUp() {
        course = CourseMock.getCourse();
    }

    @Test
    void testCreate() {
        when(courseRepository.save(course)).thenReturn(course);

        Course createdCourse = courseService.create(course);

        assertEquals(course, createdCourse);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testGet() {
        BigInteger courseId = BigInteger.ONE;
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        Course foundCourse = courseService.get(courseId);

        assertEquals(course, foundCourse);
        verify(courseRepository, times(1)).findById(courseId);
    }

    @Test
    void testGet_NotFound() {
        BigInteger courseId = BigInteger.ONE;
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> courseService.get(courseId));
        verify(courseRepository, times(1)).findById(courseId);
    }

    @Test
    void testList() {
        when(courseRepository.findAll()).thenReturn(List.of(course));

        List<Course> courses = courseService.list();

        assertEquals(List.of(course), courses);
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        BigInteger courseId = BigInteger.ONE;
        Course updatedCourse = CourseMock.getCourse();
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(courseRepository.save(course)).thenReturn(updatedCourse);

        Course result = courseService.update(courseId, updatedCourse);

        assertEquals(updatedCourse, result);
        verify(courseRepository, times(1)).findById(courseId);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testUpdate_NotFound() {
        BigInteger courseId = BigInteger.ONE;
        Course updatedCourse = CourseMock.getCourse();
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> courseService.update(courseId, updatedCourse));
        verify(courseRepository, times(1)).findById(courseId);
    }

    @Test
    void testDelete() {
        BigInteger courseId = BigInteger.ONE;

        courseService.delete(courseId);

        verify(courseRepository, times(1)).deleteById(courseId);
    }

}
