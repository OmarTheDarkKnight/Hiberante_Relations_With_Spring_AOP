package com.bat.service.interfaces;

import com.bat.model.Review;

public interface ReviewService {
    public void save(Review newReview);
    public Review getReviewById(String theId);
    public void delete(String theId);
}
