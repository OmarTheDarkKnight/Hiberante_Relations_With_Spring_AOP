package com.bat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bat.alfred.Helper;
import com.bat.dao.InstructorDao;
import com.bat.model.Instructor;
import com.bat.service.InstructorService;

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
		return instructorDao.get();
	}

	@Override
	public Instructor getById(int instructorId) {
		List instructor = instructorDao.get("id = " + instructorId);
		return instructor.isEmpty() ? null : (Instructor)instructor.get(0);
	}

	@Override
	public List getByName(String name) {
		return instructorDao.get(helper.whereLike(new String[]{"firstName", "lastName"},  "%" + name + "%"));
	}

	@Override
	public List getByEmail(String email) {
		return instructorDao.get(helper.whereLike(new String[]{"email"},  "%" + email + "%"));
	}
	
	@Override
	public List getByAnyField(String searchString) {
		return instructorDao.get(helper.whereLike(new String[]{"firstName", "lastName", "email"},  "%" + searchString + "%"));
	}

	@Override
	public void delete(int instructorId) {
		instructorDao.delete(instructorId);
	}
}
