package com.bat.dao;

import com.bat.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDao extends HelperDao {

    public void insert(Course course) {
        em.persist(course);
    }

    public void update(Course course) {
        em.merge(course);
    }

    public Course getById(Integer courseId) {
        return em.find(Course.class, courseId);
    }

    public List<Course> getAll() {
        return hibernateQuery("SELECT * FROM course", Course.class)
                .list();
    }

    public List<Course> getByTitle(String courseTitle) {
        return hibernateQuery("SELECT * FROM course WHERE title LIKE %:title%", Course.class)
                .setParameter("title", courseTitle)
                .list();
    }

    public List<Course> getByInstructor(int instructorId) {
        return hibernateQuery("SELECT * FROM course WHERE instructor_id=:instructorId", Course.class)
                .setParameter("instructorId", instructorId)
                .list();
    }

    public void delete(Course course) {
        em.remove(course);
    }
}
