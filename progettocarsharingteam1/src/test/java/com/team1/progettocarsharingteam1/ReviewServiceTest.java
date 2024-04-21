package com.team1.progettocarsharingteam1;

import com.team1.progettocarsharingteam1.entities.Rent;
import com.team1.progettocarsharingteam1.entities.Review;
import com.team1.progettocarsharingteam1.entities.enums.RatingEnum;
import com.team1.progettocarsharingteam1.repositories.ReviewRepository;
import com.team1.progettocarsharingteam1.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    private Review review;
    private Optional<Review> reviewOpt;
    private List<Review> reviewList;

    @BeforeEach
    public void setup() {

        review = new Review(1L,"Marco","ghfjoingingirn", RatingEnum.QUATTRO,new Rent());

        reviewOpt = Optional.of(review);

        reviewList = List.of(review, review);
    }

    @Test
    public void create() {
        when(this.reviewRepository.save(any())).thenReturn(review);
        Review savedReview = reviewService.create(review);

        assertThat(savedReview).isNotNull();
        assertThat(savedReview.getId()).isNotNull();
    }

    @Test
    public void findAll() {
        when(this.reviewRepository.findAllByIsActiveTrue()).thenReturn(reviewList);
        List<Review> reviewList = reviewService.findAll(true);

        assertThat(reviewList.size()).isGreaterThan(1);
    }

    @Test
    public void findById() {
        when(this.reviewRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(reviewOpt);
        Optional<Review> reviewOPT = reviewService.findById(1L);

        assertThat(reviewOPT.get().getId()).isNotNull();
    }

    @Test
    public void findById_empty() {
        when(this.reviewRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(Optional.empty());
        Optional<Review> reviewOpt = reviewService.findById(1L);

        assertThat(reviewOpt).isEmpty();
    }

    @Test
    public void edit() {
        when(this.reviewRepository.save(any())).thenReturn(review);
        when(this.reviewRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(reviewOpt);
        Optional<Review> reviewOpt = reviewService.edit(1L, review);

        assertThat(reviewOpt.get().getName()).isEqualTo(review.getName());
        assertThat(reviewOpt.get().getRating()).isEqualTo(review.getRating());
    }

    @Test
    public void edit_empty() {
        when(this.reviewRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(Optional.empty());
        Optional<Review> reviewOpt = reviewService.edit(1L, review);

        assertThat(reviewOpt).isEmpty();
    }

    @Test
    public void delete() {
        when(this.reviewRepository.findById(anyLong())).thenReturn(reviewOpt);
        Optional<Review> reviewOpt = reviewService.delete(1L);

        assertThat(reviewOpt.get().getId()).isNotNull();
    }

    @Test
    public void delete_empty() {
        when(this.reviewRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Review> reviewOpt = reviewService.delete(1L);

        assertThat(reviewOpt).isEmpty();
    }

}


