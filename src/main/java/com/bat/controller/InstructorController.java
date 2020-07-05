package com.bat.controller;

import com.bat.annotations.Exists;
import com.bat.dto.InstructorWithDetailsDto;
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
@RequestMapping("/instructor")
public class InstructorController extends BaseController {

	@GetMapping("/all")
	public String getInstructorList(Model model, RedirectAttributes redirectAttr) {
		try{
			List<InstructorWithDetailsDto> instructors = instructorService.getAllInstructorsWithDetails();
			model.addAttribute("instructors", instructors);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return "redirect:/";
		}

		return alfred.buildViewName(instructorFolderName, "list");
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

		return alfred.buildViewName(instructorFolderName, "instructorForm");
	}

	@PostMapping("/saveInstructor")
	public String saveInstructor(
			@Valid @ModelAttribute("instructor") InstructorWithDetailsDto instructorWithDetailsDto,
			BindingResult bindingResult,
			RedirectAttributes redirectAttr) {

		Map<String, String> messages = new HashMap<>();
		try {
			String checkResult = checkUnique("instructor", "email", instructorWithDetailsDto.getEmail(),
					instructorWithDetailsDto.getEncId(), "instructor");
			if(!StringUtils.isEmpty(checkResult))
				bindingResult.rejectValue("email", "", checkResult);

			if(bindingResult.hasErrors()) {
				return alfred.buildViewName(instructorFolderName, "instructorForm");
			}
			instructorService.save(instructorWithDetailsDto);
			messages.put("success", "Instructor saved successfully");
		} catch (Exception exception) {
			messages.put("error", "Could not save instructor");
		}
		redirectAttr.addFlashAttribute("messages", messages);
		return "redirect:/instructor/all";
	}

	@PostMapping("/delete")
    public String deleteInstructor(@RequestParam("theId") String theId, RedirectAttributes redirectAttr) {
        Map<String, String> messages = new HashMap<>();
        try{
            instructorService.delete(theId);
			messages.put("success", "Instructor deleted successfully");
        } catch (Exception exception) {
            messages.put("error", "Could not delete resource");
        }
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
        return alfred.buildViewName(instructorFolderName, "courses");
    }
}
