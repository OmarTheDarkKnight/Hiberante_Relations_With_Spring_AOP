package com.bat.service.impl;

import java.util.List;

import com.bat.dao.CourseDao;
import com.bat.dao.ReviewDao;
import com.bat.dto.BaseDto;
import com.bat.dto.CourseDto;
import com.bat.dto.InstructorWithCourseDto;
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

	@Autowired
	private ReviewDao reviewDao;

	@Autowired
	private BaseDto baseDto;

	private String instructorSalt = "instructor";
	private String courseSalt = "course";

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
			newInstructor.setId(baseDto.decrypt(newInstructorWithDetails.getEncId(), instructorSalt));
			instructorDetails.setId(newInstructorWithDetails.getInstructor_detail_id());
			instructorDao.update(newInstructor);
		}
	}

	@Override
	public List<InstructorWithDetailsDto> getAllInstructorsWithDetails() {
		List<InstructorWithDetailsDto> instructorWithDetailsDtos = instructorDao.getAllWithDetails();
		instructorWithDetailsDtos.forEach(dto->{
			dto.setEncId(baseDto.encrypt(String.valueOf(dto.getId()), instructorSalt));
		});
		return instructorWithDetailsDtos;
	}

	@Override
	public InstructorWithDetailsDto getInstructorFormData(String theInstructorId) {
		InstructorWithDetailsDto instructorWithDetailsDto = new InstructorWithDetailsDto();
		if(!StringUtils.isEmpty(theInstructorId)) {
			Instructor instructor = instructorDao.getById(baseDto.decrypt(theInstructorId, instructorSalt));

			// converting from entity to dto object
			instructorWithDetailsDto.setEncId(
					baseDto.encrypt(String.valueOf(instructor.getId()), // get the instructor id, convert to string and set it as first parameter for encrypt method
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
	public InstructorWithCourseDto getInstructorCourses(String theInstructorId) throws Exception {
		InstructorWithCourseDto instructorWithCourseDto = new InstructorWithCourseDto();
		if(!StringUtils.isEmpty(theInstructorId.trim())) {
			Instructor instructor = instructorDao.getById(baseDto.decrypt(theInstructorId, instructorSalt));
			instructorWithCourseDto.setEncId(theInstructorId);
			instructorWithCourseDto.setFirst_name(instructor.getFirstName());
			instructorWithCourseDto.setLast_name(instructor.getLastName());
			instructorWithCourseDto.setEmail(instructor.getEmail());

			List<CourseDto> courseDtoList = courseDao.getByInstructor(instructor.getId());
			courseDtoList.forEach(courseDto -> {
				int courseId = courseDto.getId();
				courseDto.setEncId(baseDto.encrypt(String.valueOf(courseId), courseSalt));
				courseDto.setRating(reviewDao.getAvgRatingByCourse(courseId));
			});
			instructorWithCourseDto.setCourses(courseDtoList);
		} else {
			throw new Exception("Invalid Request");
		}
		return instructorWithCourseDto;
	}

	@Override
	public void delete(String theID) {
		int instructorId = baseDto.decrypt(theID, instructorSalt);
		instructorDao.delete(instructorId);
	}
}
