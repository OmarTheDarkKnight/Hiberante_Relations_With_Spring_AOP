package com.bat.service;

import java.util.List;

import com.bat.dto.InstructorWithDetailsDto;
import com.bat.model.Instructor;

public interface InstructorService {
	void save(InstructorWithDetailsDto newInstructorWithDetails);
	List<InstructorWithDetailsDto> getAllInstructorsWithDetails();
	InstructorWithDetailsDto getInstructorFormData(String theId);
	Instructor getInstructorCourses(String theInstructorId) throws Exception;
	void delete(String theID);
}
