package com.bat.service.impl;

import com.bat.alfred.Helper;
import com.bat.dao.CourseDao;
import com.bat.model.Course;
import com.bat.model.Instructor;
import com.bat.service.CourseService;
import com.bat.service.InstructorService;
import com.bat.service.ReviewService;
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

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private InstructorService instructorService;

    @Override
    public void save(Course newCourse) {
        if(newCourse.getInstructor().getEmail() != null) {
            Instructor instructor = (Instructor) instructorService.getByEmail(newCourse.getInstructor().getEmail()).get(0);
            newCourse.setInstructor(instructor);
        }
        courseDao.save(newCourse);
    }

    @Override
    public List getAll() {
        return this.setCourseRating(courseDao.get(""));
    }

    @Override
    public Course getById(String theId) {
        int courseId = Integer.parseInt(theId);
        List course = courseDao.get("id = " + courseId);
        return course.isEmpty() ? null : (Course)this.setCourseRating(course).get(0);
    }

    @Override
    public List getByTitle(String title) {
        return this.setCourseRating(courseDao.get(alfred.whereLike(new String[]{"title"}, '%' + title + '%')));
    }

    @Override
    public List getByInstructor(int instructorId) {
        return this.setCourseRating(courseDao.get(alfred.where(new String[]{"instructor_id"}, Integer.toString(instructorId))));
    }

    @Override
    public void delete(String theId) {
        int courseId = Integer.parseInt(theId);
        courseDao.delete(courseId);
    }

    private List<Course> setCourseRating(List<Course> courses) {
        for(Course c: courses) {
            c.setRating(reviewService.getAvgRatingByCourse(c.getId()));
        }
        return courses;
    }
}
