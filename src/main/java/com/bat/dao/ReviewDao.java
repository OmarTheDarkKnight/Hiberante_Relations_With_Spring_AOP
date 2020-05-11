package com.bat.dao;

import com.bat.model.Review;

import java.util.List;

public interface ReviewDao {
    public void save(Review review);
    public List get(String whereClause);
    public void delete(int reviewId);
}
