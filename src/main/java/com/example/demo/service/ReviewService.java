package com.example.demo.service;

import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    public void saveReview(Review review) {
        repository.save(review);
    }

    public Review getReviewById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteReview(Long id) {
        repository.deleteById(id);
    }
}