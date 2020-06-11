package com.bat.service;

import java.util.List;

import com.bat.dto.InstructorWithCourseDto;
import com.bat.dto.InstructorWithDetailsDto;

public interface InstructorService {
	void save(InstructorWithDetailsDto newInstructorWithDetails);
	List<InstructorWithDetailsDto> getAllInstructorsWithDetails();
	InstructorWithDetailsDto getInstructorFormData(String theId);
	InstructorWithCourseDto getInstructorCourses(String theInstructorId) throws Exception;
	void delete(String theID);
}
