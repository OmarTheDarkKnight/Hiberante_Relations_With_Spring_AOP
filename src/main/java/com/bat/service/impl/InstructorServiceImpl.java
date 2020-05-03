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
public class InstructorServiceImpl implements InstructorService {
	@Autowired
	private InstructorDao instructorDao;
	
	@Autowired
	private Helper helper;

	@Transactional
	@Override
	public void save(Instructor newInstructor) {
		instructorDao.save(newInstructor);
	}

	@Transactional
	@Override
	public List getAll() {
		return instructorDao.get("");
	}

	@Transactional
	@Override
	public Instructor getById(String theId) {
		int instructorId = Integer.parseInt(theId);
		List instructor = instructorDao.get("id = " + instructorId);
		return instructor.isEmpty() ? null : (Instructor)instructor.get(0);
	}

	@Transactional
	@Override
	public List getByName(String name) {
		return instructorDao.get(helper.whereLike(new String[]{"firstName", "lastName"},  "%" + name + "%"));
	}

	@Transactional
	@Override
	public List getByEmail(String email) {
		return instructorDao.get(helper.whereLike(new String[]{"email"},  "%" + email + "%"));
	}
	
	@Override
	public List getByAnyField(String searchString) {
		return instructorDao.get(helper.whereLike(new String[]{"firstName", "lastName", "email"},  "%" + searchString + "%"));
	}

	@Transactional
	@Override
	public void delete(String theID) {
		int instructorId = Integer.parseInt(theID);
		instructorDao.delete(instructorId);
	}
}
