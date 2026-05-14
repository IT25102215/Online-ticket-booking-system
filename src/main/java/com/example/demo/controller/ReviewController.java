package com.example.demo.controller;

import com.example.demo.model.Review;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @GetMapping
    public String viewHomePage(Model model) {
        model.addAttribute("listReviews", service.getAllReviews());
        model.addAttribute("review", new Review());
        return "reviews";
    }

    @PostMapping("/save")
    public String saveReview(@ModelAttribute("review") Review review) {
        service.saveReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable(value = "id") Long id, Model model) {
        Review review = service.getReviewById(id);
        model.addAttribute("review", review);
        model.addAttribute("listReviews", service.getAllReviews());
        return "reviews";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable(value = "id") Long id) {
        service.deleteReview(id);
        return "redirect:/reviews";
    }
}