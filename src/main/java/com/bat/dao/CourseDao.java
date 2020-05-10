package com.bat.dao;

import com.bat.model.Course;

import java.util.List;

public interface CourseDao {
    public void save(Course course);
    public List get(String whereClause);
    public void delete(int courseId);
}
