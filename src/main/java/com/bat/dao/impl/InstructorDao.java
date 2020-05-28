package com.bat.dao.impl;

import java.util.List;

import com.bat.dao.HelperDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bat.model.Instructor;

@Repository
public class InstructorDao extends HelperDao {

	public List<Instructor> getAll() {
		return hibernateQuery("SELECT * FROM instructor WHERE 1", Instructor.class)
				.list();
	}

	public List<Instructor> getBySimilarEmail(String email) {
		return hibernateQuery("SELECT * FROM instructor WHERE email LIKE %:email%", Instructor.class)
				.setParameter("email", email)
				.list();
	}

	public Instructor getByEmail(String email) {
		return (Instructor) hibernateQuery("SELECT * FROM instructor WHERE email =:email", Instructor.class)
				.setParameter("email", email)
				.uniqueResult();
	}

	public List<Instructor> getByName(String firstName, String lastName) {
		return hibernateQuery("SELECT * FROM instructor WHERE firstName LIKE %:firstName% OR lastName LKE %:lastName%", Instructor.class)
				.setParameter("firstName", firstName)
				.setParameter("lastName", lastName)
				.list();
	}

	public Instructor getById(Integer mapID) {
		return em.find(Instructor.class, mapID);
	}

	public void insert(Instructor testEntity) {
		em.persist(testEntity);
	}

	public void update(Instructor testEntity) {
		em.merge(testEntity);
	}

	public void delete(Instructor testEntity) {
		em.remove(testEntity);
	}

}