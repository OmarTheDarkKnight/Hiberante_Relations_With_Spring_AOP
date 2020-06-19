package com.bat.dao;

import com.bat.dto.CourseDto;
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

    public List<CourseDto> getAll() {
        return hibernateQuery("SELECT c.id as id, c.title as title, c.instructor_id as instructor_id," +
                " concat(i.first_name, ' ', i.last_name) as name, i.email as email" +
                " FROM course c JOIN instructor i ON i.id = c.instructor_id", CourseDto.class)
                .list();
    }

    public List<Course> getByTitle(String courseTitle) {
        return hibernateQuery("SELECT * FROM course WHERE title LIKE %:title%", Course.class)
                .setParameter("title", courseTitle)
                .list();
    }

    public List<CourseDto> getByInstructor(int instructorId) {
        return hibernateQuery("SELECT id, title FROM course WHERE instructor_id=:instructorId", CourseDto.class)
                .setParameter("instructorId", instructorId)
                .list();
    }

    public void delete(int courseId) {
        em.remove(this.getById(courseId));
    }
}
