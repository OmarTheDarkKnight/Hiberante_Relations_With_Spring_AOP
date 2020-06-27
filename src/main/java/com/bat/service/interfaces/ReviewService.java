package com.bat.service.interfaces;

import com.bat.dto.CourseDto;
import com.bat.dto.ReviewDto;
import com.bat.model.Review;

public interface ReviewService {
    void save(Review newReview);
    CourseDto getReviewsOfACourse(String courseId);
    ReviewDto getReview(String theEncReviewId);
    void delete(String theId);
}
