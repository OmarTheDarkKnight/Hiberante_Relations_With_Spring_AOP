package com.bat.controller;

import com.bat.dto.CourseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {

    @GetMapping("/all")
    public String courseList(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return alfred.buildViewName(courseFolderName, "list");
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
        return alfred.buildViewName(courseFolderName, "courseForm");
    }

    @PostMapping("/saveCourse")
    public String saveCourse(
            @Valid @ModelAttribute("course") CourseDto theCourseDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttr) {

        Map<String, String> messages = new HashMap<>();
        try{
            if(bindingResult.hasErrors()) {
                return alfred.buildViewName(courseFolderName, "courseForm");
            }
            if(!courseService.save(theCourseDto))
                throw new Exception();
            messages.put("success", "Course added successfully");
        } catch (Exception exception) {
            messages.put("error", "Oops...something went wrong. Could not save the course.");
        }
        redirectAttr.addFlashAttribute("messages", messages);
        return "redirect:/course/all";
    }

    @PostMapping("/delete")
    public String deleteCourse(@RequestParam("theId") String theId, RedirectAttributes redirectAttr) {
        Map<String, String> messages = new HashMap<>();
        try{
            courseService.delete(theId);
            messages.put("success", "Course deleted successfully");
        } catch (Exception exception) {
            messages.put("error", "Oops...something went wrong. Could not delete resource.");
        }
        redirectAttr.addFlashAttribute("messages", messages);
        return "redirect:/course/all";
    }
}

