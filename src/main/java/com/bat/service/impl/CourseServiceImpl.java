package com.bat.service.impl;

import com.bat.dao.CourseDao;
import com.bat.dao.InstructorDao;
import com.bat.dao.ReviewDao;
import com.bat.dto.BaseDto;
import com.bat.dto.CourseDto;
import com.bat.model.Course;
import com.bat.model.Instructor;
import com.bat.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private BaseDto baseDto;

    @Autowired
    private InstructorDao instructorDao;

    private String courseSalt = "course";
    private String instructorSalt = "parent";

    @Override
    public void save(Course newCourse) {
//        if(newCourse.getInstructor().getEmail() != null) {
//            Instructor instructor = (Instructor) instructorService.getByEmail(newCourse.getInstructor().getEmail()).get(0);
//            newCourse.setInstructor(instructor);
//        }
//        courseDao.save(newCourse);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<CourseDto> courseDtoList = courseDao.getAll();
        courseDtoList.forEach(courseDto -> {
            int courseId = courseDto.getId();
            courseDto.setEncId(baseDto.encrypt(String.valueOf(courseId), courseSalt));
            courseDto.setRating(reviewDao.getAvgRatingByCourse(courseId));
            courseDto.setEncInstructor_id(baseDto.encrypt(String.valueOf(courseDto.getInstructor_id()), instructorSalt));
        });
        return courseDtoList;
    }

    @Override
    public CourseDto getCourseWithInstructor(String theCourseId, String theInstructorId) throws Exception {
        CourseDto courseDto = new CourseDto();
        // If there's a course then check for instructor
        if(!StringUtils.isEmpty(theCourseId)) {
            // If no instructor then it is an invalid request
            // throw an exception for this scenario
            if(StringUtils.isEmpty(theInstructorId)) throw new Exception("Invalid request. No action available");
            else {
                // fetch that course with instructor no need to check for instructor
                courseDto = courseDao.getCourseByIdWithInstructor(baseDto.decrypt(theCourseId, courseSalt));
                courseDto.setEncId(baseDto.encrypt(String.valueOf(courseDto.getId()), courseSalt));
                courseDto.setEncInstructor_id(baseDto.encrypt(String.valueOf(courseDto.getInstructor_id()), instructorSalt));
            }
        } else {
            // If there's no course and an instructor then fetch that
            if(!StringUtils.isEmpty(theInstructorId)) {
                Instructor instructor = instructorDao.getById(baseDto.decrypt(theInstructorId, instructorSalt));
                courseDto.setEncInstructor_id(baseDto.encrypt(String.valueOf(instructor.getId()), instructorSalt));
                courseDto.setEmail(instructor.getEmail());
            }
        }

        return courseDto;
    }

    @Override
    public void delete(String theId) {
        courseDao.delete(baseDto.decrypt(theId, courseSalt));
    }
}
