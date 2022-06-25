package com.example.backend.mapper;

import com.example.backend.dto.SummerCourseDTO;
import com.example.backend.entity.SummerCourse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SummerCourseMapper {

    SummerCourse toSummerCourse(SummerCourseDTO summerCourseDTO);

    SummerCourseDTO toSummerCourseDTO(SummerCourse summerCourse);

    List<SummerCourseDTO> toListSummerCourseDTO(List<SummerCourse> summerCourses);
}
