package com.rrsthiago.skillboost.service;

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
        Course createdCourse = courseRepository.save(course);

        return createdCourse;
    }

    public Course get(BigInteger id) {
        Course course = courseRepository.findById(id).orElse(null);

        return course;
    }

    public List<Course> list() {
        List<Course> courses = courseRepository.findAll();

        return courses;
    }

    public Course update(BigInteger id, Course course) {
        Course updatedCourse = courseRepository.save(course);

        return updatedCourse;
    }

    public void delete(BigInteger id) {
        courseRepository.deleteById(id);
    }

}
