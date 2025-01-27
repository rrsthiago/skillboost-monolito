package com.rrsthiago.skillboost.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "syllabus")
    private String syllabus;

    @Column(name = "total_hours")
    private Integer totalHours;

    @Column(name = "goal_point")
    private Integer goalPoint;

    @OneToMany
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Set<CourseActivity> courseActivities;

}
