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
		
		// build query with the pre-built @whereClause string
		Query<Instructor> query = session.createQuery("from Instructor where " + whereClause, Instructor.class);
		List<Instructor> instructors = query.getResultList();
		
		return instructors;
	}
	
	@Override
	public List get() {
		return this.get("1");
	}

	@Override
	public void delete(int instructorId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Customer where id=:instructorId");
		query.setParameter("instructorId", instructorId);
		query.executeUpdate();
	}

}
