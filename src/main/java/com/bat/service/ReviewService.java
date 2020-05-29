package com.bat.service;

import com.bat.model.Review;

import java.util.List;

public interface ReviewService {
    public void save(Review newReview);
    public Review getReviewById(String theId);
    public void delete(String theId);
}
