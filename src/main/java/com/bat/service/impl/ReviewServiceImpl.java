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

    @Override
    public void save(Review newReview) {
//        reviewDao.save(newReview);
    }

    @Override
    public Review getReviewById(String theId) {
        int reviewId = Integer.parseInt(theId);
        return reviewDao.getById(reviewId);
    }

    @Override
    public void delete(String theId) {
        int reviewId = Integer.parseInt(theId);
        reviewDao.delete(reviewId);
    }
}
