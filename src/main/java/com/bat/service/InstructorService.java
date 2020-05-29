package com.bat.service;

import java.util.List;

import com.bat.model.Instructor;

public interface InstructorService {
	void save(Instructor newInstructor);
	List<Instructor> getAllInstructors();
	Instructor getInstructorFormData(String theId);
	Instructor getInstructorCourses(String theInstructorId) throws Exception;
	void delete(String theID);
}
