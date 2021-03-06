package com.bat.dao;

import com.bat.dto.ReviewDto;
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

    public List<ReviewDto> getByCourse(int  courseId) {
        return hibernateQuery("SELECT id, rating, comment FROM review WHERE course_id=:courseId", ReviewDto.class)
                .setParameter("courseId", courseId)
                .list();
    }

    public List<Review> getByCourseAndRating(int  courseId, float rating) {
        return hibernateQuery("SELECT * FROM review WHERE course_id=:courseId AND rating=:rating", Review.class)
                .setParameter("courseId", courseId)
                .setParameter("rating", rating)
                .list();
    }

    public float getAvgRatingByCourse(int courseId) {
        Double avg = (Double) hibernateQuery("SELECT IFNULL(avg(rating), 0) FROM review WHERE course_id=:courseId")
                .setParameter("courseId", courseId)
                .getSingleResult();
        return avg.floatValue();
    }

    public void delete(int reviewId) {
        em.remove(this.getById(reviewId));
    }
}
