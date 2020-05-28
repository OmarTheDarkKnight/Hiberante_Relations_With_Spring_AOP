package com.bat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bat.alfred.Helper;
import com.bat.dao.InstructorDao;
import com.bat.model.Instructor;
import com.bat.service.InstructorService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {
	@Autowired
	private InstructorDao instructorDao;
	
	@Autowired
	private Helper helper;

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
	public List<Instructor> getAllInstructors() {
		return instructorDao.getAll();
	}

	@Override
	public Instructor getInstructorById(String theId) {
		int instructorId = Integer.parseInt(theId);
		return instructorDao.getById(instructorId);
	}

	@Override
	public void delete(String theID) {
		int instructorId = Integer.parseInt(theID);
		instructorDao.delete(instructorId);
	}
}
