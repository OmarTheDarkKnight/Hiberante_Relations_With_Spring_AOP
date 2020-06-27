package com.bat.service.interfaces;

import com.bat.dto.CourseDto;
import com.bat.dto.ReviewDto;

public interface ReviewService {
    Boolean save(ReviewDto reviewDto);
    CourseDto getReviewsOfACourse(String courseId);
    ReviewDto getReview(String theEncReviewId, String theParentId);
    void delete(String theId);
}
