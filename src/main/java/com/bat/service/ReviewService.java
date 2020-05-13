package com.bat.service;

import com.bat.model.Review;

import java.util.List;

public interface ReviewService {
    public void save(Review newReview);
    public Review getById(String theId);
    public List getByCourse(int reviewId);
    public List getByCourseAndRating(int reviewId, float rating);
    public float getAvgRatingByCourse(int courseId);
    public void delete(String theId);
}
