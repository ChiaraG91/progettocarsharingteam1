package com.team1.progettocarsharingteam1.controllers;

import com.team1.progettocarsharingteam1.entities.Review;
import com.team1.progettocarsharingteam1.entities.enums.RatingEnum;
import com.team1.progettocarsharingteam1.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Review", description = "api related to the reviews")
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);

    @Operation(summary = "create a review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully created"),
            @ApiResponse(responseCode = "404", description = "Failed to create the review")
    })
    @PostMapping("/create")
    public ResponseEntity<Review> create(@RequestBody Review review) {
        Review reviewNuova = reviewService.create(review);
        LOGGER.info("creation done");
        return ResponseEntity.ok().body(reviewNuova);
    }

    @Operation(summary = "find all reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews successfully found"),
            @ApiResponse(responseCode = "404", description = "Reviews not found")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Review>> findAll(@RequestParam(required = false, defaultValue = "true") boolean isActive) {
        List<Review> reviews = reviewService.findAll(isActive);
        LOGGER.info("operation completed");
        return ResponseEntity.ok().body(reviews);
    }

    @Operation(summary = "find a review by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully found"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<Review> findById(@PathVariable Long id) {
        Optional<Review> reviewOpt = reviewService.findById(id);
        if (reviewOpt.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok().body(reviewOpt.get());
        } else {
            LOGGER.info("review not found");
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "update a review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully updated"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<Review> edit(@PathVariable Long id, @RequestBody Review review) {
        Optional<Review> reviewOpt = reviewService.edit(id, review);
        if (reviewOpt.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok().body(reviewOpt.get());
        } else {
            LOGGER.info("review not found");
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "delete a review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Review> delete(@PathVariable Long id) {
        Optional<Review> reviewOpt = reviewService.delete(id);
        if (reviewOpt.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok().body(reviewOpt.get());
        } else {
            LOGGER.info("review not found");
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Find all reviews by rating enum
     *
     * @param ratingEnum the rating enum
     * @return the list of review found
     */
    @Operation(summary = "find a review by stars")
    @Description("find a review choosing a rating from 1 to 5 number or return error 404 if not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review found"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @GetMapping("/findByRating")
    public ResponseEntity<List<Review>> findByRating(@RequestParam RatingEnum ratingEnum) {
        List<Review> reviews = reviewService.findByRating(ratingEnum);
        if (reviews.isEmpty()) {
            LOGGER.info("review not found");
            return ResponseEntity.notFound().build();
        } else {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(reviews);
        }
    }

    /**
     * Find all reviews sorted by rating higher to lower
     *
     * @return the list of review found
     */
    @Operation(summary = "find all review sorted by higher rating")
    @Description("find all review sorted by higher rating or return error 404 if not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review found"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @GetMapping("/sorted")
    public ResponseEntity<List<Review>> sortByRating() {
        List<Review> reviews = reviewService.sortedRating();
        if (reviews.isEmpty()) {
            LOGGER.info("review not found");
            return ResponseEntity.notFound().build();
        } else {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(reviews);
        }
    }

    /**
     * Endpoint for updating the isActive field of a review
     *
     * @param id       the identifier of the review to be updated
     * @param isActive the boolean value to set for the isActive field
     * @return ResponseEntity containing the updated review, or a 404 Not Found response if the review is not found.
     */
    @Operation(summary = "delete the review")
    @Description("perform a logical delete of a review using the id or return error 404 if the review its not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review found"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @PutMapping("/edit-active/{id}")
    public ResponseEntity<Review> editActive(@PathVariable Long id, @RequestParam boolean isActive) {
        Optional<Review> reviewOpt = reviewService.editActive(id, isActive);
        if (reviewOpt.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok().body(reviewOpt.get());
        }
        LOGGER.info("review not found");
        return ResponseEntity.notFound().build();
    }

}
