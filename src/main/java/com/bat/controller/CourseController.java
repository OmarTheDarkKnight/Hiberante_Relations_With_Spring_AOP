package com.bat.controller;

import com.bat.alfred.Helper;
import com.bat.model.Course;
import com.bat.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private Helper alfred;

    private String folderName = "course";

    @GetMapping("/all")
    public String courseList(Model model) {
        List<Course> courses = courseService.getAll();
        model.addAttribute("courses", courses);
        return alfred.buildViewName(folderName, "list");
    }
}

