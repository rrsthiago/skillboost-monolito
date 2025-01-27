package com.rrsthiago.skillboost.repository;

import com.rrsthiago.skillboost.model.CourseActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface CourseActivityRepository extends JpaRepository<CourseActivity, BigInteger> {

    List<CourseActivity> findAllByCourseId(BigInteger courseId);

}
