package com.bat.dao;

import com.bat.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstructorDao extends HelperDao {
	public void insert(Instructor instructor) {
		em.persist(instructor);
	}

	public void update(Instructor instructor) {
		em.merge(instructor);
	}

	public Instructor getById(Integer mapID) {
		return em.find(Instructor.class, mapID);
	}

	public List<Instructor> getAll() {
		return hibernateQuery("SELECT * FROM instructor", Instructor.class)
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

	public void delete(Instructor instructor) {
		em.remove(instructor);
	}

}