package com.bat.service;

import java.util.List;

import com.bat.model.Instructor;

public interface InstructorService {
	public void save(Instructor newInstructor);
	public List<Instructor> getAllInstructors();
	public Instructor getInstructorFormData(String theId);
	public void delete(String theID);
}
