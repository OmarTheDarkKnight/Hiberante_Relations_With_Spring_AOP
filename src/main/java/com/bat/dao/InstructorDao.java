package com.bat.dao;

import java.util.List;

import com.bat.model.Instructor;

public interface InstructorDao {
	public void save(Instructor newInstructor);
	public List get(String whereClause);
	public List getByEmail(String email);
	public void delete(int instructorId);
}
