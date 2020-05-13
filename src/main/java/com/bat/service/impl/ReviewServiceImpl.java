package com.bat.service.impl;

import com.bat.alfred.Helper;
import com.bat.dao.ReviewDao;
import com.bat.model.Review;
import com.bat.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private Helper alfred;

    @Override
    public void save(Review newReview) {
        reviewDao.save(newReview);
    }

    @Override
    public Review getById(String theId) {
        int reviewId = Integer.parseInt(theId);
        List reviews = reviewDao.get("id = " + reviewId);
        return reviews.isEmpty() ? null : (Review)reviews.get(0);
    }

    @Override
    public List getByCourse(int reviewId) {
        return reviewDao.get(alfred.where(new String[]{"course_id"}, String.valueOf(reviewId)));
    }

    @Override
    public List getByCourseAndRating(int reviewId, float rating) {
        List<String[][]> whereClause = new ArrayList<>();
        whereClause.add(new String[][]{new String[]{"course_id"}, {"="}, {String.valueOf(reviewId)}});
        whereClause.add(new String[][]{new String[]{"rating"}, {"="}, {String.valueOf(rating)}});

        return reviewDao.get(alfred.whereAnd(whereClause));
    }

    @Override
    public float getAvgRatingByCourse(int courseId) {
        return reviewDao.getAvg(courseId);
    }

    @Override
    public void delete(String theId) {
        int reviewId = Integer.parseInt(theId);
        reviewDao.delete(reviewId);
    }
}
