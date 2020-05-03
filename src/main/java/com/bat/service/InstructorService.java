package com.bat.service;

import java.util.List;

import com.bat.model.Instructor;

public interface InstructorService {
	public void save(Instructor newInstructor);
	public List getAll();
	public Instructor getById(String theId);
	public List getByName(String name);
	public List getByEmail(String email);
	public List getByAnyField(String searchString);
	public void delete(String theID);
}
