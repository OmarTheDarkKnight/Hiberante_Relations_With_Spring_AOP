package com.bat.dao.impl;

import com.bat.dao.HelperDao;
import com.bat.model.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDao extends HelperDao {
    public void insert(Review review) {
        em.persist(review);
    }

    public void update(Review review) {
        em.merge(review);
    }

    public Review getById(Integer reviewId) {
        return em.find(Review.class, reviewId);
    }

    public List<Review> getByCourse(int  courseId) {
        return hibernateQuery("SELECT * FROM review WHERE course_id=:courseId", Review.class)
                .setParameter("courseId", courseId)
                .list();
    }

    public List<Review> getByCourseAndRating(int  courseId, float rating) {
        return hibernateQuery("SELECT * FROM review WHERE course_id=:courseId AND rating:=rating", Review.class)
                .setParameter("courseId", courseId)
                .setParameter("rating", rating)
                .list();
    }

    public List<Review> getByInstructor(int instructorId) {
        return hibernateQuery("SELECT * FROM review WHERE instructor_id=:instructorId%", Review.class)
                .setParameter("instructorId", instructorId)
                .list();
    }

    public float getAvgRatingByCourse(int courseId) {
        Double avg = (Double) hibernateQuery("SELECT COALESCE(avg(rating), 0) FROM Review WHERE course_id:=courseId", Review.class)
                .setParameter("courseId", courseId)
                .getSingleResult();
        return avg.floatValue();
    }

    public void delete(Review review) {
        em.remove(review);
    }
}
