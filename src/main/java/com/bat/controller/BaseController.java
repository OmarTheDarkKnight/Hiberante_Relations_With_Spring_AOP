package com.bat.controller;

import com.bat.alfred.Helper;
import com.bat.service.interfaces.CourseService;
import com.bat.service.interfaces.InstructorService;
import com.bat.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public abstract class BaseController {
    @Autowired
    protected Helper alfred;

    @Autowired
    protected InstructorService instructorService;

    @Autowired
    protected CourseService courseService;

    @Autowired
    protected ReviewService reviewService;

    protected String instructorFolderName = "instructor";
    protected String courseFolderName = "course";
    protected String reviewFolderName = "reviews";

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // Spring class for string trim
        // "true" in the constructor means it will trim the spaces down to null
        // if there's only white spaces in the data filed
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);

        // registering it as a custom editor that will work on every String class as specified
        webDataBinder.registerCustomEditor(String.class, stringTrimmer);
    }
}
