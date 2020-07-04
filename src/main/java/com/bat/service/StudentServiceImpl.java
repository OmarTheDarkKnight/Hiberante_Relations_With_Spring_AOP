package com.bat.service;

import com.bat.dto.StudentWithCourseDto;
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
			if(StringUtils.isEmpty(newStudentWithCourseDto.getEncId())) {
				//insert
			} else {
				//update
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
	public void delete(String theID) {
		studentDao.delete(decrypt(theID, studentSalt));
	}
}
