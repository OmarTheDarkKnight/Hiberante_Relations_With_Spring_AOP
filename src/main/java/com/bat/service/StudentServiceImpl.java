package com.bat.service;

import com.bat.dto.CourseDto;
import com.bat.dto.StudentWithCourseDto;
import com.bat.model.Name;
import com.bat.model.Student;
import com.bat.service.interfaces.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl extends BaseService implements StudentService {

	@Override
	public Boolean save(StudentWithCourseDto newStudentWithCourseDto) {
		try {
			Student student = new Student(
					new Name(newStudentWithCourseDto.getFirst_name(), newStudentWithCourseDto.getLast_name()),
					newStudentWithCourseDto.getEmail()
			);
			if(StringUtils.isEmpty(newStudentWithCourseDto.getEncId())) {
				//insert
				studentDao.insert(student);
			} else {
				//update
				student.setId(decrypt(newStudentWithCourseDto.getEncId(), studentSalt));
				studentDao.update(student);
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public List<StudentWithCourseDto> getAllStudents() {
		List<StudentWithCourseDto> students = studentDao.getAll();
		students.forEach(student -> {
			student.setEncId(encrypt(student.getId(), studentSalt));
		});
		return students;
	}

	@Override
	public StudentWithCourseDto getStudentById(String studentId) {
		StudentWithCourseDto studentWithCourseDto = new StudentWithCourseDto();
		if(!StringUtils.isEmpty(studentId)) {
			Student student = studentDao.getById(decrypt(studentId, studentSalt));
			studentWithCourseDto.setEncId(encrypt(student.getId(), studentSalt));
			studentWithCourseDto.setFirst_name(student.getName().getFirstName());
			studentWithCourseDto.setLast_name(student.getName().getLastName());
			studentWithCourseDto.setEmail(student.getEmail());
		}
		return studentWithCourseDto;
	}

	@Override
	public StudentWithCourseDto getCoursesOfStudent(String studentId) {
		Student student = studentDao.getById(decrypt(studentId, studentSalt));

		// convert from model to dto
		StudentWithCourseDto studentWithCourseDto = new StudentWithCourseDto(student.getName().getFirstName(), student.getName().getLastName(),
				student.getEmail());
		studentWithCourseDto.setEncId(encrypt(student.getId(), studentSalt));

		// fetch course list and set them in studentWithCourseDto
		List<CourseDto> courses = courseDao.getCoursesByStudent(student.getId());
		courses.forEach(course -> {
			course.setRating(reviewDao.getAvgRatingByCourse(course.getId()));
			course.setEncId(encrypt(course.getId(), courseSalt));
		});
		studentWithCourseDto.setCourses(courses);

		return studentWithCourseDto;
	}

	@Override
	public void delete(String theID) {
		studentDao.delete(decrypt(theID, studentSalt));
	}
}
