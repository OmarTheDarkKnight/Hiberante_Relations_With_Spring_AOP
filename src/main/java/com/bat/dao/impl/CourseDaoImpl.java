package com.bat.dao.impl;

import com.bat.dao.CourseDao;
import com.bat.model.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Course newCourse) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(newCourse);
    }

    @Override
    public List get(String whereClause) {
        Session session = sessionFactory.getCurrentSession();
        String builtQuery = "from Course";

        // if @whereClause is "" then add it with @builtQuery else add " where " first, then @whereClause
        // @whereClause "" empty means fetch all records
        builtQuery += whereClause.isEmpty() ? whereClause : " where " + whereClause;
        Query<Course> query = session.createQuery(builtQuery, Course.class);

        return query.getResultList();
    }

    @Override
    public void delete(int courseId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Course where id=:theCourseId");
        query.setParameter("theCourseId", courseId);
        query.executeUpdate();
    }
}
