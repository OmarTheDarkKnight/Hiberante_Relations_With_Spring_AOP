package com.bat.service.interfaces;

import com.bat.dto.CourseDto;
import com.bat.model.Review;

public interface ReviewService {
    void save(Review newReview);
    Review getReviewById(String theId);
    CourseDto getReviewsOfACourse(String courseId);
    void delete(String theId);
}
