package com.bat.service.impl;

import java.util.List;

import com.bat.dao.CourseDao;
import com.bat.dto.BaseDto;
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

	private String instructorSalt = "instructor";

	/*
	* Decryption of the id will be done here
	* */
	private Instructor getById(String theId) {
		int instructorId = Integer.parseInt(theId);
		return instructorDao.getById(instructorId);
	}

	@Override
	public void save(InstructorWithDetailsDto newInstructorWithDetails) {
		InstructorDetails instructorDetails = new InstructorDetails(
				newInstructorWithDetails.getYoutube_channel(),
				newInstructorWithDetails.getHobby()
		);

		Instructor newInstructor = new Instructor(
				newInstructorWithDetails.getFirst_name(),
				newInstructorWithDetails.getLast_name(),
				newInstructorWithDetails.getEmail()
		);
		newInstructor.setInstructorDetails(instructorDetails);
		if(StringUtils.isEmpty(newInstructorWithDetails.getEncId())) {
			instructorDao.insert(newInstructor);
		} else {
			newInstructor.setId(newInstructorWithDetails.decrypt(newInstructorWithDetails.getEncId(), instructorSalt));
			instructorDetails.setId(newInstructorWithDetails.getInstructor_detail_id());
			instructorDao.update(newInstructor);
		}
	}

	@Override
	public List<InstructorWithDetailsDto> getAllInstructorsWithDetails() {
		List<InstructorWithDetailsDto> instructorWithDetailsDtos = instructorDao.getAllWithDetails();
		instructorWithDetailsDtos.forEach(dto->{
			dto.setEncId(dto.encrypt(String.valueOf(dto.getId()), instructorSalt));
		});
		return instructorWithDetailsDtos;
	}

	@Override
	public InstructorWithDetailsDto getInstructorFormData(String theInstructorId) {
		InstructorWithDetailsDto instructorWithDetailsDto = new InstructorWithDetailsDto();
		if(!StringUtils.isEmpty(theInstructorId)) {
			Instructor instructor = instructorDao.getById(instructorWithDetailsDto.decrypt(theInstructorId, instructorSalt));

			// converting from entity to dto object
			instructorWithDetailsDto.setEncId(
					instructorWithDetailsDto.encrypt(String.valueOf(instructor.getId()), // get the instructor id, convert to string and set it as first parameter for encrypt method
					instructorSalt) // second parameter of the encrypt method
			);
			instructorWithDetailsDto.setFirst_name(instructor.getFirstName());
			instructorWithDetailsDto.setLast_name(instructor.getLastName());
			instructorWithDetailsDto.setEmail(instructor.getEmail());

			instructorWithDetailsDto.setInstructor_detail_id(instructor.getInstructorDetails().getId());
			instructorWithDetailsDto.setHobby(instructor.getInstructorDetails().getHobby());
			instructorWithDetailsDto.setYoutube_channel(instructor.getInstructorDetails().getYouTubeChannel());
		}

		return instructorWithDetailsDto;
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
