package com.bat.dao;

import com.bat.dto.StudentWithCourseDto;
import com.bat.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao extends HelperDao {
	public void insert(Student student) {
		em.persist(student);
	}

	public void update(Student student) {
		em.merge(student);
	}

	public Student getById(Integer mapID) {
		return em.find(Student.class, mapID);
	}

	public List<StudentWithCourseDto> getAll() {
		return hibernateQuery("SELECT id, first_name, last_name, email FROM student", StudentWithCourseDto.class).list();
	}

	public StudentWithCourseDto getByEmail(String email) {
		return (StudentWithCourseDto) hibernateQuery("SELECT id, first_name, last_name, email FROM student WHERE email =:email", StudentWithCourseDto.class)
				.setParameter("email", email)
				.getSingleResult();
	}

	public List<StudentWithCourseDto> getStudentsByCourse(int courseId) {
		return hibernateQuery("SELECT s.id, s.first_name as first_name, s.last_name as last_name, s.email as email "
				+ "FROM student S JOIN student_course sc ON sc.student_id = s.id " +
				"WHERE sc.course_id=:courseId", StudentWithCourseDto.class).
				setParameter("courseId", courseId)
				.list();
	}

	public void delete(int studentId) {
		em.remove(this.getById(studentId));
	}

}