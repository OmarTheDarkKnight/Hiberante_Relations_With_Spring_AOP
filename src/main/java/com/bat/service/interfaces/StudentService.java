package com.bat.service.interfaces;

import com.bat.dto.StudentWithCourseDto;

import java.util.List;

public interface StudentService {
	Boolean save(StudentWithCourseDto newStudentWithCourseDto);
	List<StudentWithCourseDto> getAllStudents();
	StudentWithCourseDto getStudentById(String studentId);
	void delete(String theID);
}
