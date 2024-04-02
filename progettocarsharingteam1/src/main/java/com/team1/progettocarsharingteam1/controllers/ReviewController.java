package com.team1.progettocarsharingteam1.controllers;

import com.team1.progettocarsharingteam1.entities.Rent;
import com.team1.progettocarsharingteam1.entities.Review;
import com.team1.progettocarsharingteam1.entities.enums.RatingEnum;
import com.team1.progettocarsharingteam1.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> create(@RequestBody Review review) {
        Review reviewNuova = reviewService.create(review);
        return ResponseEntity.ok().body(reviewNuova);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Review>> findAll(@RequestParam(required = false, defaultValue = "true") boolean isActive) {
        List<Review> reviews = reviewService.findAll(isActive);
        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Review> findById(@PathVariable Long id) {
        Optional<Review> reviewOpt = reviewService.findById(id);
        if (reviewOpt.isPresent()) {
            return ResponseEntity.ok().body(reviewOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Review> edit(@PathVariable Long id, @RequestBody Review review) {
        Optional<Review> reviewOpt = reviewService.edit(id, review);
        if (reviewOpt.isPresent()) {
            return ResponseEntity.ok().body(reviewOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Review> delete(@PathVariable Long id) {
        Optional<Review> reviewOpt = reviewService.delete(id);
        if (reviewOpt.isPresent()) {
            return ResponseEntity.ok().body(reviewOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Find all reviews by rating enum
     *
     * @param ratingEnum the rating enum
     * @return the list of review found
     */
    @GetMapping("/findByRating")
    public ResponseEntity<List<Review>> findByRating(@RequestParam RatingEnum ratingEnum) {
        List<Review> reviews = reviewService.findByRating(ratingEnum);
        if (reviews.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(reviews);
        }
    }

    /**
     * Find all reviews sorted by rating higher to lower
     *
     * @return the list of review found
     */
    @GetMapping("/sorted")
    public ResponseEntity<List<Review>> sortByRating() {
        List<Review> reviews = reviewService.sortedRating();
        if (reviews.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(reviews);
        }
    }

    @PutMapping("/edit-active/{id}")
    public ResponseEntity<Review> editActive(@PathVariable Long id, @RequestParam boolean isActive) {
        Optional<Review> reviewOpt = reviewService.editActive(id, isActive);

        if (reviewOpt.isPresent()) {
            return ResponseEntity.ok().body(reviewOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

}
