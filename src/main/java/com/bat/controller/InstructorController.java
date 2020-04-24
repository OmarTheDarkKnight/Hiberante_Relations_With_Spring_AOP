package com.bat.controller;

import com.bat.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bat.alfred.Helper;
import com.bat.service.InstructorService;

import java.util.List;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private Helper helper;
	
	@GetMapping("/all")
	public String getInstructorList(Model model) {
		List<Instructor> instructors = instructorService.getAll();
		model.addAttribute("instructors", instructors);
		return helper.buildViewName("instructor", "list");
	}
}
