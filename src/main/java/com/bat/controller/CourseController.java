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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return alfred.buildViewName(folderName, "list");
    }

    @GetMapping("/course-form")
    public String showCourseForm(@RequestParam(value = "target", required = false) String theId,
                                 @RequestParam(value = "parent", required = false) String theParentId,
                                 Model model, RedirectAttributes redirectAttr) {
        try {
            model.addAttribute("course", courseService.getCourseWithInstructor(theId, theParentId));
        } catch (Exception exception) {
            Map<String, String> messages = new HashMap<String, String>();
            messages.put("error", exception.getMessage());
            redirectAttr.addFlashAttribute("messages", messages);
            return "redirect:/course/all";
        }
        return alfred.buildViewName(folderName, "courseForm");
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@Valid @ModelAttribute("course") Course theCourse,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return alfred.buildViewName(folderName, "courseForm");
        }
        courseService.save(theCourse);
        return "redirect:/course/all";
    }
}

