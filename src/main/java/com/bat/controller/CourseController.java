package com.bat.controller;

import com.bat.alfred.Helper;
import com.bat.dto.CourseDto;
import com.bat.service.interfaces.CourseService;
import com.bat.service.interfaces.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private Helper alfred;

    private String folderName = "course";

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // Spring class for string trim
        // "true" in the constructor means it will trim the spaces down to null
        // if there's only white spaces in the data filed
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);

        // registering it as a custom editor that will work on every String class as specified
        webDataBinder.registerCustomEditor(String.class, stringTrimmer);
    }

    @GetMapping("/all")
    public String courseList(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
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
    public String saveCourse(
            @Valid @ModelAttribute("course") CourseDto theCourseDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttr) {

        if(bindingResult.hasErrors()) {
            return alfred.buildViewName(folderName, "courseForm");
        }
        Map<String, String> messages = new HashMap<>();
        try{
            if(!courseService.save(theCourseDto))
                throw new Exception();
        } catch (Exception exception) {
            messages.put("error", "Oops...something went wrong. Could not save the course.");
            redirectAttr.addFlashAttribute("messages", messages);
        }

        messages.put("success", "Course added successfully");
        redirectAttr.addFlashAttribute("messages", messages);
        return "redirect:/course/all";
    }

    @PostMapping("/delete")
    public String deleteCourse(@RequestParam("theId") String theId, RedirectAttributes redirectAttr) {
        Map<String, String> messages = new HashMap<>();
        try{
            courseService.delete(theId);
        } catch (Exception exception) {
            messages.put("error", "Oops...something went wrong. Could not delete resource.");
            redirectAttr.addFlashAttribute("messages", messages);
            return "redirect:/course/all";
        }

        messages.put("success", "Course deleted successfully");
        redirectAttr.addFlashAttribute("messages", messages);
        return "redirect:/course/all";
    }
}

