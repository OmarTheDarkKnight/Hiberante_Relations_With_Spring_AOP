package com.bat.service;

import com.bat.dto.CourseDto;
import com.bat.model.Course;

import java.util.List;

public interface CourseService {
    public void save(Course newCourse);
    public List<CourseDto> getAllCourses();
    public CourseDto getCourseWithInstructor(String theCourseId, String theInstructorId) throws Exception;
    public void delete(String theId);
}
