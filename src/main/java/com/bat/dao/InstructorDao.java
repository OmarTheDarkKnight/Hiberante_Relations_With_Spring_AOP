package com.bat.dao;

import com.bat.dto.InstructorWithDetailsDto;
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

	public List<InstructorWithDetailsDto> getAllWithDetails() {
		return hibernateQuery("SELECT i.id, i.first_name, i.last_name, i.email, i.instructor_detail_id, d.hobby, d.youtube_channel" +
				" FROM instructor i" +
				" JOIN instructor_detail d ON d.id = i.instructor_detail_id", InstructorWithDetailsDto.class)
				.list();
	}

//	public List<Instructor> getBySimilarEmail(String email) {
//		return hibernateQuery("SELECT * FROM instructor WHERE email LIKE %:email%", Instructor.class)
//				.setParameter("email", email)
//				.list();
//	}

	public InstructorWithDetailsDto getByEmail(String email) {
		return (InstructorWithDetailsDto) hibernateQuery("SELECT id, first_name, last_name, email FROM instructor WHERE email =:email", InstructorWithDetailsDto.class)
				.setParameter("email", email)
				.getSingleResult();
	}

//	public List<Instructor> getByName(String name) {
//		return hibernateQuery("SELECT * FROM instructor WHERE firstName LIKE %:name% OR lastName LKE %:name%", Instructor.class)
//				.setParameter("name", name)
//				.list();
//	}

	public void delete(int instructorId) {
		em.remove(this.getById(instructorId));
	}

}