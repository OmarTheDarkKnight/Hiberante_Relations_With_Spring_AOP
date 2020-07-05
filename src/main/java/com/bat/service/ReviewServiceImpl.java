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
    public Boolean save(ReviewDto reviewDto) {
        try{
            if(StringUtils.isEmpty(reviewDto.getEncId())) {
                //insert
                Review newReview = new Review(reviewDto.getComment(), reviewDto.getRating());
                newReview.setCourse(courseDao.getById(decrypt(reviewDto.getEncCourse_id())));
                reviewDao.insert(newReview);
            } else {
                //update
                Review review = reviewDao.getById(decrypt(reviewDto.getEncId()));
                review.setComment(reviewDto.getComment());
                review.setRating(reviewDto.getRating());
                reviewDao.update(review);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public CourseDto getReviewsOfACourse(String courseId) {
        CourseDto courseDto = courseDao.getCourseByIdWithInstructor(decrypt(courseId));
        courseDto.setEncId(encrypt(courseDto.getId()));
        courseDto.setRating(reviewDao.getAvgRatingByCourse(courseDto.getId()));

        List<ReviewDto> reviewDtoList = reviewDao.getByCourse(courseDto.getId());
        reviewDtoList.forEach(review-> {
            review.setEncId(encrypt(review.getId()));
        });
        courseDto.setReviewDtoList(reviewDtoList);
        return courseDto;
    }

    @Override
    public ReviewDto getReview(String theEncReviewId, String theParentId) {
        ReviewDto reviewDto = new ReviewDto();
        if(!StringUtils.isEmpty(theEncReviewId)) {
            Review review = reviewDao.getById(decrypt(theEncReviewId));
            reviewDto.setEncId(encrypt(review.getId()));
            reviewDto.setEncCourse_id(encrypt(review.getCourse().getId()));
            reviewDto.setComment(review.getComment());
            reviewDto.setRating((float)review.getRating());
        } else {
            reviewDto.setEncCourse_id(theParentId);
        }
        return reviewDto;
    }

    @Override
    public void delete(String theId) {
        reviewDao.delete(decrypt(theId));
    }
}
