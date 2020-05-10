package com.bat.service;

import com.bat.model.Course;

import java.util.List;

public interface CourseService {
    public void save(Course newCourse);
    public List getAll();
    public Course getById(String theId);
    public List getByTitle(String title);
    public List getByInstructor(int instructorId);
    public void delete(String theId);
}
