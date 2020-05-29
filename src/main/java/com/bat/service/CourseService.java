package com.bat.service;

import com.bat.model.Course;

import java.util.List;

public interface CourseService {
    public void save(Course newCourse);
    public List<Course> getAllCourses();
    public Course getCourseWithInstructor(String theCourseId, String theInstructorId) throws Exception;
    public void delete(String theId);
}
