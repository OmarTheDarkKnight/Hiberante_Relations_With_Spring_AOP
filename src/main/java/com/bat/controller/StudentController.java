package com.bat.controller;

import com.bat.dto.StudentWithCourseDto;
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
@RequestMapping("/student")
public class StudentController extends BaseController {

	@GetMapping("/all")
	public String getInstructorList(Model model, RedirectAttributes redirectAttr) {
		try{
			List<StudentWithCourseDto> students = studentService.getAllStudents();
			model.addAttribute("students", students);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return "redirect:/";
		}

		return alfred.buildViewName(studentFolderName, "list");
	}

	@GetMapping("/student-form")
	public String showInstructorForm(@RequestParam(value = "target", required = false) String theId,
									 Model model, RedirectAttributes redirectAttr) {
		try{
			StudentWithCourseDto student = studentService.getStudentById(theId);
			model.addAttribute("student", student);
		} catch (Exception exception) {
			Map<String, String> messages = new HashMap<>();
			messages.put("error", "Could not find resource");
			redirectAttr.addFlashAttribute("messages", messages);
			return "redirect:/student/all";
		}

		return alfred.buildViewName(studentFolderName, "studentForm");
	}

	@PostMapping("/save-student")
	public String saveInstructor(
			@Valid @ModelAttribute("student") StudentWithCourseDto studentWithCourseDto,
			BindingResult bindingResult,
			RedirectAttributes redirectAttr) {

		Map<String, String> messages = new HashMap<>();
		try {
			String checkResult = checkExistsOrUnique(studentWithCourseDto.getEncId(), "student", "email", studentWithCourseDto.getEmail());
			if(!StringUtils.isEmpty(checkResult))
				bindingResult.rejectValue("email", "", checkResult);

			if(bindingResult.hasErrors()) {
				return alfred.buildViewName(studentFolderName, "studentForm");
			}
			studentService.save(studentWithCourseDto);
			messages.put("success", "Student saved successfully");
		} catch (Exception exception) {
			messages.put("error", "Could not save student");
		}
		redirectAttr.addFlashAttribute("messages", messages);
		return "redirect:/student/all";
	}

	@PostMapping("/delete")
    public String deleteInstructor(@RequestParam("theId") String theId, RedirectAttributes redirectAttr) {
        Map<String, String> messages = new HashMap<>();
        try{
            studentService.delete(theId);
			messages.put("success", "Student deleted successfully");
        } catch (Exception exception) {
            messages.put("error", "Could not delete student");
        }
		redirectAttr.addFlashAttribute("messages", messages);
	    return "redirect:/student/all";
    }

    @GetMapping("/courses")
    public String showInstructorCourses(@RequestParam("target") String theId, Model model, RedirectAttributes redirectAttr) {

		try{
			// TODO : Add method to fetch courses for a specific student and call it from controller
//			model.addAttribute("instructor", studentService.getInstructorCourses(theId));
		} catch (Exception exception) {
			Map<String, String> messages = new HashMap<>();
			messages.put("error", "Oops...Could not fetch data. Please try again.");
			redirectAttr.addFlashAttribute("messages", messages);
			return "redirect:/instructor/all";
		}
        return alfred.buildViewName(studentFolderName, "courses");
    }
}
