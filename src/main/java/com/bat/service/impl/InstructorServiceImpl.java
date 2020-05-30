package com.bat.service.impl;

import java.util.List;

import com.bat.dao.CourseDao;
import com.bat.dto.InstructorDto;
import com.bat.dto.InstructorWithDetailsDto;
import com.bat.model.InstructorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bat.dao.InstructorDao;
import com.bat.model.Instructor;
import com.bat.service.InstructorService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {
	@Autowired
	private InstructorDao instructorDao;

	@Autowired
	private CourseDao courseDao;

	/*
	* Decryption of the id will be done here
	* */
	private Instructor getById(String theId) {
		int instructorId = Integer.parseInt(theId);
		return instructorDao.getById(instructorId);
	}

	@Override
	public void save(Instructor newInstructor) {
//		if(newInstructor.getId() == 0) {
//
//		} else {
//
//		}
//		instructorDao.insert(newInstructor);
	}

	@Override
	public List<InstructorWithDetailsDto> getAllInstructorsWithDetails() {
		List<InstructorWithDetailsDto> instructorWithDetailsDtos = instructorDao.getAllWithDetails();
		instructorWithDetailsDtos.forEach(dto->{
			dto.setEncId(dto.encrypt(dto.getId(), "instructor"));
		});
		return instructorWithDetailsDtos;
	}

	@Override
	public Instructor getInstructorFormData(String theInstructorId) {
		Instructor instructor = null;
		if(!StringUtils.isEmpty(theInstructorId)) {
			instructor = this.getById(theInstructorId);
		} else {
			instructor = new Instructor();
			instructor.setInstructorDetails(new InstructorDetails());
		}

		return instructor;
	}

	@Override
	public Instructor getInstructorCourses(String theInstructorId) throws Exception {
		Instructor instructor = null;
		if(!StringUtils.isEmpty(theInstructorId)) {
			instructor = this.getById(theInstructorId);
			instructor.setCourses(courseDao.getByInstructor(instructor.getId()));
		} else {
			throw new Exception("Invalid Request");
		}
		return instructor;
	}

	@Override
	public void delete(String theID) {
		int instructorId = Integer.parseInt(theID);
		instructorDao.delete(instructorId);
	}
}
