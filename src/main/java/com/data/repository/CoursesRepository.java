package com.data.repository;

import com.data.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    List<Courses> findByCoursesNameContaining(String coursesName);
    List<Courses> findByCoursesName(String coursesName);
}
