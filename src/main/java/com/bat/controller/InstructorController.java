package com.bat.controller;

import com.bat.dto.InstructorWithDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.bat.alfred.Helper;
import com.bat.service.InstructorService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private Helper helper;

	private String folderName = "instructor";

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
	public String getInstructorList(Model model, RedirectAttributes redirectAttr) {
		try{
			List<InstructorWithDetailsDto> instructors = instructorService.getAllInstructorsWithDetails();
			model.addAttribute("instructors", instructors);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return "redirect:/";
		}

		return helper.buildViewName(folderName, "list");
	}

	@GetMapping("/instructor-form")
	public String showInstructorForm(@RequestParam(value = "target", required = false) String theId,
									 Model model, RedirectAttributes redirectAttr) {
		try{
			InstructorWithDetailsDto instructor = instructorService.getInstructorFormData(theId);
			model.addAttribute("instructor", instructor);
		} catch (Exception exception) {
			Map<String, String> messages = new HashMap<>();
			messages.put("error", "Could not find resource");
			redirectAttr.addFlashAttribute("messages", messages);
			return "redirect:/instructor/all";
		}

		return helper.buildViewName(folderName, "instructorForm");
	}

	@PostMapping("/saveInstructor")
	public String saveInstructor(
			@Valid @ModelAttribute("instructor") InstructorWithDetailsDto instructorWithDetailsDto,
			BindingResult bindingResult,
			RedirectAttributes redirectAttr) {

		try {
			if(bindingResult.hasErrors()) {
				return helper.buildViewName(folderName, "instructorForm");
			}
			instructorService.save(instructorWithDetailsDto);
		} catch (Exception exception) {
			Map<String, String> messages = new HashMap<>();
			messages.put("error", "Could not save instructor");
			redirectAttr.addFlashAttribute("messages", messages);
		}

		return "redirect:/instructor/all";
	}

	@PostMapping("/delete")
    public String deleteInstructor(@RequestParam("theId") String theId, RedirectAttributes redirectAttr) {
        Map<String, String> messages = new HashMap<>();
        try{
            instructorService.delete(theId);
        } catch (Exception exception) {
            messages.put("error", "Could not delete resource");
            redirectAttr.addFlashAttribute("messages", messages);
            return "redirect:/instructor/all";
        }

        messages.put("success", "Instructor deleted successfully");
        redirectAttr.addFlashAttribute("messages", messages);
	    return "redirect:/instructor/all";
    }

    @GetMapping("/courses")
    public String showInstructorCourses(@RequestParam("target") String theId, Model model, RedirectAttributes redirectAttr) {

		try{
			model.addAttribute("instructor", instructorService.getInstructorCourses(theId));
		} catch (Exception exception) {
			Map<String, String> messages = new HashMap<>();
			messages.put("error", exception.getMessage());
			redirectAttr.addFlashAttribute("messages", messages);
			return "redirect:/instructor/all";
		}
        return helper.buildViewName(folderName, "courses");
    }
}
