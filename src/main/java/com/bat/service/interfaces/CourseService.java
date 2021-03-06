package com.bat.service.interfaces;

import com.bat.dto.CourseDto;

import java.util.List;

public interface CourseService {
    Boolean save(CourseDto newCourse);
    List<CourseDto> getAllCourses();
    CourseDto getCourseWithInstructor(String theCourseId, String theInstructorId) throws Exception;
    CourseDto getStudentsOfCourse(String courseId);
    void delete(String theId);
}
