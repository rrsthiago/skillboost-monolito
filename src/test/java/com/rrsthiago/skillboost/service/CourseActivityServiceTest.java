package com.rrsthiago.skillboost.service;

import com.rrsthiago.skillboost.exception.ResourceNotFoundException;
import com.rrsthiago.skillboost.model.CourseActivity;
import com.rrsthiago.skillboost.repository.CourseActivityRepository;
import mock.CourseActivityMock;
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
class CourseActivityServiceTest {

    @InjectMocks
    private CourseActivityService courseActivityService;

    @Mock
    private CourseService courseService;

    @Mock
    private CourseActivityRepository courseActivityRepository;

    private CourseActivity courseActivity;

    @BeforeEach
    void setUp() {
        courseActivity = CourseActivityMock.getCourseActivity();
    }

    @Test
    void testCreate() {
        BigInteger courseId = BigInteger.ONE;
        when(courseService.get(courseId)).thenReturn(null);
        when(courseActivityRepository.save(courseActivity)).thenReturn(courseActivity);

        CourseActivity createdCourseActivity = courseActivityService.create(courseId, courseActivity);

        assertEquals(courseActivity, createdCourseActivity);
        verify(courseService, times(1)).get(courseId);
        verify(courseActivityRepository, times(1)).save(courseActivity);
    }

    @Test
    void testGet() {
        BigInteger courseId = BigInteger.ONE;
        BigInteger activityId = BigInteger.ONE;
        when(courseActivityRepository.findById(activityId)).thenReturn(Optional.of(courseActivity));

        CourseActivity foundCourseActivity = courseActivityService.get(courseId, activityId);

        assertEquals(courseActivity, foundCourseActivity);
        verify(courseActivityRepository, times(1)).findById(activityId);
    }

    @Test
    void testGet_NotFound() {
        BigInteger courseId = BigInteger.ONE;
        BigInteger activityId = BigInteger.ONE;
        when(courseActivityRepository.findById(activityId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> courseActivityService.get(courseId, activityId));
        verify(courseActivityRepository, times(1)).findById(activityId);
    }

    @Test
    void testList() {
        BigInteger courseId = BigInteger.ONE;
        when(courseActivityRepository.findAllByCourseId(courseId)).thenReturn(List.of(courseActivity));

        List<CourseActivity> courseActivities = courseActivityService.list(courseId);

        assertEquals(List.of(courseActivity), courseActivities);
        verify(courseActivityRepository, times(1)).findAllByCourseId(courseId);
    }

    @Test
    void testUpdate() {
        BigInteger courseId = BigInteger.ONE;
        BigInteger activityId = BigInteger.ONE;
        CourseActivity updatedCourseActivity = CourseActivityMock.getCourseActivity();
        when(courseActivityRepository.findById(activityId)).thenReturn(Optional.of(courseActivity));
        when(courseActivityRepository.save(courseActivity)).thenReturn(updatedCourseActivity);

        CourseActivity result = courseActivityService.update(courseId, activityId, updatedCourseActivity);

        assertEquals(updatedCourseActivity, result);
        verify(courseActivityRepository, times(1)).findById(activityId);
        verify(courseActivityRepository, times(1)).save(courseActivity);
    }

    @Test
    void testUpdate_NotFound() {
        BigInteger courseId = BigInteger.ONE;
        BigInteger activityId = BigInteger.ONE;
        CourseActivity updatedCourseActivity = CourseActivityMock.getCourseActivity();
        when(courseActivityRepository.findById(activityId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> courseActivityService.update(courseId, activityId, updatedCourseActivity));
        verify(courseActivityRepository, times(1)).findById(activityId);
    }

    @Test
    void testDelete() {
        BigInteger courseId = BigInteger.ONE;
        BigInteger activityId = BigInteger.ONE;

        courseActivityService.delete(courseId, activityId);

        verify(courseActivityRepository, times(1)).deleteById(activityId);
    }

}
