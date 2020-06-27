package com.bat.service;

import com.bat.dto.CourseDto;
import com.bat.dto.ReviewDto;
import com.bat.model.Review;
import com.bat.service.interfaces.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl extends BaseService implements ReviewService {
    @Override
    public void save(Review newReview) {
//        reviewDao.save(newReview);
    }

    @Override
    public CourseDto getReviewsOfACourse(String courseId) {
        CourseDto courseDto = courseDao.getCourseByIdWithInstructor(decrypt(courseId, courseSalt));
        courseDto.setEncId(encrypt(courseDto.getId(), courseSalt));
        courseDto.setRating(reviewDao.getAvgRatingByCourse(courseDto.getId()));

        List<ReviewDto> reviewDtoList = reviewDao.getByCourse(courseDto.getId());
        reviewDtoList.forEach(review-> {
            review.setEncId(encrypt(review.getId(), reviewWSalt));
        });
        courseDto.setReviewDtoList(reviewDtoList);
        return courseDto;
    }

    @Override
    public ReviewDto getReview(String theEncReviewId) {
        ReviewDto reviewDto = new ReviewDto();
        if(!StringUtils.isEmpty(theEncReviewId)) {
            Review review = reviewDao.getById(decrypt(theEncReviewId, reviewWSalt));
            reviewDto.setEncId(encrypt(review.getId(), reviewWSalt));
            reviewDto.setComment(review.getComment());
            reviewDto.setRating(review.getRating());
        }
        return reviewDto;
    }

    @Override
    public void delete(String theId) {
        int reviewId = Integer.parseInt(theId);
        reviewDao.delete(reviewId);
    }
}
