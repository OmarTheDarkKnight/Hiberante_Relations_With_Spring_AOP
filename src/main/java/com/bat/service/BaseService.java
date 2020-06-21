package com.bat.service;

import com.bat.dao.CourseDao;
import com.bat.dao.InstructorDao;
import com.bat.dao.ReviewDao;
import com.bat.dto.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
    @Autowired
    protected CourseDao courseDao;

    @Autowired
    protected ReviewDao reviewDao;

    @Autowired
    protected InstructorDao instructorDao;

    @Autowired
    protected BaseDto baseDto;

    protected String courseSalt = "course";
    protected String instructorSalt = "instructor";

    public String encrypt(int intToEnc, String salt) {
        return baseDto.encrypt(String.valueOf(intToEnc), salt);
    }

    public int decrypt(String strToDec, String salt) {
        return baseDto.decrypt(strToDec, salt);
    }
}
