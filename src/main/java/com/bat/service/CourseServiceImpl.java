package com.bat.service;

import com.bat.dto.CourseDto;
import com.bat.dto.InstructorWithDetailsDto;
import com.bat.dto.StudentWithCourseDto;
import com.bat.model.Course;
import com.bat.model.Instructor;
import com.bat.model.Name;
import com.bat.service.interfaces.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl extends BaseService implements CourseService {
    @Override
    public Boolean save(CourseDto courseDto) {
        try{
            if(StringUtils.isEmpty(courseDto.getEncId())) {
                //insert
                InstructorWithDetailsDto instructorDto = instructorDao.getByEmail(courseDto.getEmail());
                Instructor instructor =
                        new Instructor(
                                new Name(instructorDto.getFirst_name(), instructorDto.getLast_name()),
                                instructorDto.getEmail()
                        );
                instructor.setId(instructorDto.getId());

                Course newCourse = new Course(courseDto.getTitle());
                newCourse.setInstructor(instructor);
                courseDao.insert(newCourse);
            } else {
                //update
                Course course = courseDao.getById(decrypt(courseDto.getEncId(), courseSalt));
                course.setTitle(courseDto.getTitle());
                courseDao.update(course);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<CourseDto> courseDtoList = courseDao.getAll();
        courseDtoList.forEach(courseDto -> {
            courseDto.setEncId(encrypt(courseDto.getId(), courseSalt));
            courseDto.setRating(reviewDao.getAvgRatingByCourse(courseDto.getId()));
            courseDto.setEncInstructor_id(encrypt(courseDto.getInstructor_id(), instructorSalt));
        });
        return courseDtoList;
    }

    @Override
    public CourseDto getCourseWithInstructor(String theCourseId, String theInstructorId) throws Exception {
        CourseDto courseDto = new CourseDto();
        if(!StringUtils.isEmpty(theCourseId)) { // If there's a course then check for instructor
            // If no instructor then it is an invalid request
            if(StringUtils.isEmpty(theInstructorId)) throw new Exception("Invalid request. No action available"); // throw an exception for this scenario
            else {
                courseDto = courseDao.getCourseByIdWithInstructor(decrypt(theCourseId, courseSalt)); // else fetch that course with instructor no need to check for instructor
                courseDto.setEncId(encrypt(courseDto.getId(), courseSalt));
                courseDto.setEncInstructor_id(encrypt(courseDto.getInstructor_id(), instructorSalt));
            }
        } else {
            if(!StringUtils.isEmpty(theInstructorId)) { // If there's no course and an instructor then fetch that
                Instructor instructor = instructorDao.getById(decrypt(theInstructorId, instructorSalt));
                courseDto.setEncInstructor_id(encrypt(instructor.getId(), instructorSalt));
                courseDto.setEmail(instructor.getEmail());
            }
        }

        return courseDto;
    }

    @Override
    public CourseDto getStudentsOfCourse(String courseId) {
        Course course = courseDao.getById(decrypt(courseId, courseSalt));

        // model to dto conversion
        CourseDto courseDto = new CourseDto(
                encrypt(course.getId(), courseSalt),
                course.getTitle(), 0
//                reviewDao.getAvgRatingByCourse(course.getId())
        );

        // fetch the students
        List<StudentWithCourseDto> students = studentDao.getStudentsByCourse(course.getId());
        courseDto.setStudents(students);

        return  courseDto;
    }

    @Override
    public void delete(String theId) {
        courseDao.delete(decrypt(theId, courseSalt));
    }
}
