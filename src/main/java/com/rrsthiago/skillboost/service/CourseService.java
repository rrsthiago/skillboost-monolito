package com.rrsthiago.skillboost.service;

import com.rrsthiago.skillboost.exception.ResourceNotFoundException;
import com.rrsthiago.skillboost.model.Course;
import com.rrsthiago.skillboost.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public Course get(BigInteger id) {
        return findCourseById(id);
    }

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Course update(BigInteger id, Course course) {
        var existingCourse = findCourseById(id);
        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setSyllabus(course.getSyllabus());
        existingCourse.setTotalHours(course.getTotalHours());
        existingCourse.setGoalPoint(course.getGoalPoint());

        return courseRepository.save(existingCourse);
    }

    public void delete(BigInteger id) {
        courseRepository.deleteById(id);
    }

    private Course findCourseById(BigInteger id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
