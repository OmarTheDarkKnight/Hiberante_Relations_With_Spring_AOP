package com.bat.controller;

import com.bat.model.Instructor;
import com.bat.model.InstructorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.bat.alfred.Helper;
import com.bat.service.InstructorService;

import javax.validation.Valid;
import java.util.List;

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
	public String getInstructorList(Model model) {
		List<Instructor> instructors = instructorService.getAll();
		model.addAttribute("instructors", instructors);
		return helper.buildViewName(folderName, "list");
	}

	@GetMapping("/instructor-form")
	public String showInstructorForm(@RequestParam("target") String instructorId, Model model) {
		if(!StringUtils.isEmpty(instructorId)) {
			try{
				int theId = Integer.parseInt(instructorId);
				Instructor instructor = instructorService.getById(theId);
				model.addAttribute("instructor", instructor);
			} catch (Exception exception) {
				return "redirect:/instructor/all";
			}
		} else {
			Instructor instructor = new Instructor();
			instructor.setInstructorDetails(new InstructorDetails());
			model.addAttribute("instructor", instructor);
		}
		return helper.buildViewName(folderName, "instructorForm");
	}
}
