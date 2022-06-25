package com.example.backend.repository;

import com.example.backend.entity.SummerCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummerCourseRepository extends JpaRepository<SummerCourse, Integer> {
}
