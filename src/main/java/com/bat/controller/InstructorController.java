package com.bat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bat.alfred.Helper;
import com.bat.service.InstructorService;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private Helper helper;
	
	@GetMapping("/")
	public String getInstructorList(Model model) {
		model.addAttribute("instructors", instructorService.getAll());
		return helper.buildViewName("instructor", "list");
	}
}
