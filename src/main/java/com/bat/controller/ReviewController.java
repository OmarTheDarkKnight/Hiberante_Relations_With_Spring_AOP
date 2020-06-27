package com.bat.controller;

import com.bat.alfred.Helper;
import com.bat.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/review-form")
    public String getReview(@RequestParam(value = "parent") String theParentId,
                            @RequestParam(value = "target", required = false) String theId,
                            Model model, RedirectAttributes redirectAttr) {
        try {
            model.addAttribute("review", reviewService.getReview(theId));
        } catch (Exception exception) {
            Map<String, String> messages = new HashMap<String, String>();
            messages.put("error", "No review found");
            redirectAttr.addFlashAttribute("messages", messages);
            return "redirect:/reviews/" + theParentId;
        }
        return alfred.buildViewName(folderName, "review_form");
    }

    @PostMapping("/delete")
    public String deleteReview(@RequestParam("theId") String theId,
                               @RequestParam("thePId") String theParentId,
                               RedirectAttributes redirectAttr) {
        Map<String, String> messages = new HashMap<>();
        try{
            reviewService.delete(theId);
            messages.put("success", "Review deleted successfully");
        } catch (Exception exception) {
            messages.put("error", "Oops...something went wrong. Could not delete resource.");
        }
        redirectAttr.addFlashAttribute("messages", messages);
        return "redirect:/reviews/" + theParentId;
    }
}
