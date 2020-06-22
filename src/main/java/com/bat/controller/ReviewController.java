package com.bat.controller;

import com.bat.alfred.Helper;
import com.bat.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private Helper alfred;

    @Autowired
    private ReviewService reviewService;

    private String folderName = "reviews";

    @GetMapping("/{courseId}")
    public String showReviewsOfACourse(@PathVariable("courseId") String courseId, Model model, RedirectAttributes redirectAttr) {
        try {
            model.addAttribute("course", reviewService.getReviewsOfACourse(courseId));
        } catch (Exception exception) {
            Map<String, String> messages = new HashMap<String, String>();
            messages.put("error", "Something went wrong. Please try again.");
            redirectAttr.addFlashAttribute("messages", messages);
            return "redirect:/course/all";
        }
        return alfred.buildViewName(folderName, "reviews_of_course");
    }
}
