package com.bat.controller;

import com.bat.alfred.Helper;
import com.bat.model.Course;
import com.bat.model.Instructor;
import com.bat.service.CourseService;
import com.bat.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private Helper alfred;

    private String folderName = "course";

    @GetMapping("/all")
    public String courseList(Model model) {
        List<Course> courses = courseService.getAll();
        model.addAttribute("courses", courses);
        return alfred.buildViewName(folderName, "list");
    }

    @GetMapping("/course-form")
    public String showCourseForm(@RequestParam(value = "target", required = false) String theId,
                                 @RequestParam(value = "parent", required = false) String theParentId,
                                 Model model, RedirectAttributes redirectAttr) {
        try {
            Course course = null;
            // If there's a course but no parent then it is an invalid request
            // throw an exception for this scenario
            if(!StringUtils.isEmpty(theId) && StringUtils.isEmpty(theParentId)) {
                throw new Exception("Invalid request. No action available");
            }
            else if(!StringUtils.isEmpty(theId)) {
                // If there's a course then fetch that course
                // no need to check the parent here
                course = courseService.getById(theId);
            }
            else {
                // If there's no course then assign a new course object and check for parent
                course = new Course();
                if(!StringUtils.isEmpty(theParentId)) {
                    // If there's parent then set that Instructor in the course
                    course.setInstructor(instructorService.getById(theParentId));
                } else {
                    // If there's no parent then the instructor will also ne a new object
                    course.setInstructor(new Instructor());
                }
            }
            model.addAttribute("course", course);
        } catch (Exception exception) {
            Map<String, String> messages = new HashMap<String, String>();
            messages.put("error", exception.getMessage());
            redirectAttr.addFlashAttribute("messages", messages);
            return "redirect:/course/all";
        }
        return alfred.buildViewName(folderName, "courseForm");
    }
}

