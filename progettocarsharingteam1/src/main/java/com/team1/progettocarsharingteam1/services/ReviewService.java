package com.team1.progettocarsharingteam1.services;

import com.team1.progettocarsharingteam1.entities.Review;
import com.team1.progettocarsharingteam1.entities.enums.RatingEnum;
import com.team1.progettocarsharingteam1.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public Review create(Review review) {
        Review reviewNuova = reviewRepository.save(review);
        return reviewNuova;
    }

    public List<Review> findAll(boolean isActive) {

        if(isActive) {
            List<Review> reviewList = reviewRepository.findAllByIsActiveTrue();
            return reviewList;
        }
        List<Review> reviewList = reviewRepository.findAll();
        return reviewList;
    }

    public Optional<Review> findById(Long id) {
        Optional<Review> reviewOpt = reviewRepository.findByIdAndIsActiveTrue(id);
        if (reviewOpt.isPresent()) {
            return reviewOpt;
        } else {
            return Optional.empty();
        }
    }

    public Optional<Review> edit(Long id, Review review) {
        Optional<Review> reviewOpt = reviewRepository.findByIdAndIsActiveTrue(id);
        if (reviewOpt.isPresent()) {
            reviewOpt.get().setName(review.getName());
            reviewOpt.get().setDescription(review.getDescription());
            reviewOpt.get().setRating(review.getRating());
            reviewOpt.get().setRent(review.getRent());
            Review reviewUpdated = reviewRepository.save(reviewOpt.get());
            return Optional.of(reviewUpdated);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Review> delete(Long id) {
        Optional<Review> reviewOpt = reviewRepository.findById(id);
        if (reviewOpt.isPresent()) {
            reviewRepository.deleteById(id);
            return reviewOpt;
        } else {
            return Optional.empty();
        }
    }

    /**
     * find all review by rating enum
     *
     * @param ratingEnum the rating enum
     * @return the list of review found
     */
    public List<Review> findByRating(RatingEnum ratingEnum) {
        return reviewRepository.findByRatingAndIsActiveTrue(ratingEnum);
    }

    /**
     * find all review sorted by rating higher to lower
     *
     * @return the list of review found
     */
    public List<Review> sortedRating() {
        return reviewRepository.sortByRating();
    }

    public Optional<Review> editActive(Long id, boolean isActive) {
        Optional<Review> reviewOpt = reviewRepository.findById(id);

        if (reviewOpt.isPresent()){
            reviewOpt.get().setActive(isActive);
            reviewRepository.save(reviewOpt.get());

        } else {
            return Optional.empty();
        }
        return reviewOpt;
    }

}

