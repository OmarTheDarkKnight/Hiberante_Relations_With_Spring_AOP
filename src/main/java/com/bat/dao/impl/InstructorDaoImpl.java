package com.bat.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bat.dao.InstructorDao;
import com.bat.model.Instructor;

@Repository
public class InstructorDaoImpl implements InstructorDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Instructor newInstructor) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(newInstructor);
	}

	@Override
	public List get(String whereClause) {
		Session session = sessionFactory.getCurrentSession();
		String builtQuery = "from Instructor";

		// if @whereClause is "" then add it with @builtQuery else add " where " first, then @whereClause
		// @whereClause "" empty means fetch all records
		builtQuery += whereClause.isEmpty() ? whereClause : " where " + whereClause;
		Query<Instructor> query = session.createQuery(builtQuery, Instructor.class);

		return query.getResultList();
	}

	@Override
	public void delete(int instructorId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Instructor where id=:instructorId");
		query.setParameter("instructorId", instructorId);
		query.executeUpdate();
	}

}
