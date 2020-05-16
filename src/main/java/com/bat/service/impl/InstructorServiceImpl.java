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
		instructorDao.save(newInstructor);
	}

	@Override
	public List getAll() {
		return instructorDao.get("");
	}

	@Override
	public Instructor getById(String theId) {
		int instructorId = Integer.parseInt(theId);
		List instructor = instructorDao.get("id = " + instructorId);
		return instructor.isEmpty() ? null : (Instructor)instructor.get(0);
	}

	@Override
	public List getByName(String name) {
		return instructorDao.get(helper.whereLike(new String[]{"firstName", "lastName"},  "%" + name + "%"));
	}

	@Override
	public List getByEmail(String email) {
		return instructorDao.getByEmail(email);
	}
	
	@Override
	public List getByAnyField(String searchString) {
		return instructorDao.get(helper.whereLike(new String[]{"firstName", "lastName", "email"},  "%" + searchString + "%"));
	}

	@Override
	public void delete(String theID) {
		int instructorId = Integer.parseInt(theID);
		instructorDao.delete(instructorId);
	}
}
