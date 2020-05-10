package com.bat.service.impl;

import com.bat.alfred.Helper;
import com.bat.dao.CourseDao;
import com.bat.model.Course;
import com.bat.model.Instructor;
import com.bat.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private Helper alfred;

    @Override
    public void save(Course newCourse) {
        courseDao.save(newCourse);
    }

    @Override
    public List getAll() {
        return courseDao.get("");
    }

    @Override
    public Course getById(String theId) {
        int courseId = Integer.parseInt(theId);
        List course = courseDao.get("id = " + courseId);
        return course.isEmpty() ? null : (Course)course.get(0);
    }

    @Override
    public List getByTitle(String title) {
        return courseDao.get(alfred.whereLike(new String[]{"title"}, '%' + title + '%'));
    }

    @Override
    public List getByInstructor(int instructorId) {
        return courseDao.get(alfred.where(new String[]{"instructor_id"}, Integer.toString(instructorId)));
    }

    @Override
    public void delete(String theId) {
        int courseId = Integer.parseInt(theId);
        courseDao.delete(courseId);
    }
}
