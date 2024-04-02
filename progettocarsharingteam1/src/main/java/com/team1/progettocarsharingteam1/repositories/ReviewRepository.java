package com.team1.progettocarsharingteam1.repositories;

import com.team1.progettocarsharingteam1.entities.Review;
import com.team1.progettocarsharingteam1.entities.enums.RatingEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRatingAndIsActiveTrue(RatingEnum ratingEnum);

    @Query(value = "SELECT * FROM Reviews WHERE is_active = true ORDER BY rating DESC", nativeQuery = true)
    List<Review> sortByRating();

    Optional<Review> findByIdAndIsActiveTrue(Long id);

    List<Review> findAllByIsActiveTrue();
}
