package com.bat.service.impl;

import com.bat.dao.CourseDao;
import com.bat.dao.InstructorDao;
import com.bat.dao.ReviewDao;
import com.bat.model.Course;
import com.bat.model.Instructor;
import com.bat.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private InstructorDao instructorDao;

    @Override
    public void save(Course newCourse) {
//        if(newCourse.getInstructor().getEmail() != null) {
//            Instructor instructor = (Instructor) instructorService.getByEmail(newCourse.getInstructor().getEmail()).get(0);
//            newCourse.setInstructor(instructor);
//        }
//        courseDao.save(newCourse);
    }

    @Override
    public List<Course> getAllCourses() {
        return this.setCourseRating(courseDao.getAll());
    }

    @Override
    public Course getCourseWithInstructor(String theCourseId, String theInstructorId) throws Exception {
        Course course = null;
        // If there's a course but an instructor then it is an invalid request
        // throw an exception for this scenario
        if(!StringUtils.isEmpty(theCourseId) && StringUtils.isEmpty(theInstructorId)) {
            throw new Exception("Invalid request. No action available");
        }
        else if(!StringUtils.isEmpty(theCourseId)) {
            // If there's a course then fetch that course
            // no need to check for the instructor here
            int courseId = Integer.parseInt(theCourseId);
            course = this.setCourseRating(courseDao.getById(courseId));
        }
        else {
            // If there's no course then assign a new course object and check instructor
            course = new Course();
            if(!StringUtils.isEmpty(theInstructorId)) {
                // If there's an instructor then set that Instructor in the course
                int instructorId = Integer.parseInt(theInstructorId);
                course.setInstructor(instructorDao.getById(instructorId));
            } else {
                // If there's no parent then the instructor will also ne a new object
                course.setInstructor(new Instructor());
            }
        }

        return course;
    }

    @Override
    public void delete(String theId) {
        int courseId = Integer.parseInt(theId);
        courseDao.delete(courseId);
    }

    private List<Course> setCourseRating(List<Course> courses) {
        for(Course c: courses) {
            c.setRating(reviewDao.getAvgRatingByCourse(c.getId()));
        }
        return courses;
    }

    private Course setCourseRating(Course course) {
        course.setRating(reviewDao.getAvgRatingByCourse(course.getId()));
        return course;
    }
}
