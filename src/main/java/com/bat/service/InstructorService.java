package com.bat.service;

import java.util.List;

import com.bat.dto.InstructorDto;
import com.bat.dto.InstructorWithDetailsDto;
import com.bat.model.Instructor;

public interface InstructorService {
	void save(Instructor newInstructor);
	List<InstructorWithDetailsDto> getAllInstructorsWithDetails();
	Instructor getInstructorFormData(String theId);
	Instructor getInstructorCourses(String theInstructorId) throws Exception;
	void delete(String theID);
}
