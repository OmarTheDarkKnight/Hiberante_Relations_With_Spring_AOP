package com.bat.dao.impl;

import com.bat.dao.ReviewDao;
import com.bat.model.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDaoImpl implements ReviewDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Review newReview) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(newReview);
    }

    @Override
    public List get(String whereClause) {
        Session session = sessionFactory.getCurrentSession();
        String builtQuery = "from Review";

        // if @whereClause is "" then add it with @builtQuery else add " where " first, then @whereClause
        // @whereClause "" empty means fetch all records
        builtQuery += whereClause.isEmpty() ? whereClause : " where " + whereClause;
        Query<Review> query = session.createQuery(builtQuery, Review.class);

        return query.getResultList();
    }

    @Override
    public void delete(int reviewId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Review where id=:theReviewId");
        query.setParameter("theReviewId", reviewId);
        query.executeUpdate();
    }
}
